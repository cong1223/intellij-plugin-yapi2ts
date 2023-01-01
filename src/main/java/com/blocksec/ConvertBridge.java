package com.blocksec;

import com.blocksec.components.Toast;
import com.blocksec.utils.JSONArray;
import com.blocksec.utils.JSONObject;
import com.blocksec.utils.Json2ts;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilBase;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * json转ts主要逻辑
 */

public class ConvertBridge {

    private Project project;
    private Editor editor;
    private String jsonStr;
    private PsiFile psiFile;

    public ConvertBridge(String jsonStr, Project project, Editor editor) {
        this.jsonStr = jsonStr;
        this.project = project;
        this.editor = editor;
        this.psiFile = PsiUtilBase.getPsiFileInEditor(editor, project);
    }

    public void run() {
        JSONObject json = null;
        try {
            json = parseJSONObject(jsonStr);
        } catch (Exception e) {
            String jsonTS = removeComment(jsonStr);
            jsonTS = jsonTS.replaceAll("^.*?\\{", "{");
            try {
                json = parseJSONObject(jsonTS);
            } catch (Exception e2) {
                handleDataError(e2);
            }
        }
        if (json != null) {
            try {
                parseJson(json);
            } catch (Exception e2) {
                handleDataError(e2);
            }
        }
    }

    private JSONObject getJsonObject(JSONArray jsonArray) {
        JSONObject resultJSON = jsonArray.getJSONObject(0);
        for (int i = 1; i < jsonArray.length(); i++) {
            Object value = jsonArray.get(i);
            if (!(value instanceof JSONObject)) {
                break;
            }
            JSONObject json = (JSONObject) value;
            for (String key : json.keySet()) {
                if (!resultJSON.keySet().contains(key)) {
                    resultJSON.put(key, json.get(key));
                }
            }
        }
        return resultJSON;
    }


    private JSONObject parseJSONObject(String jsonStr) {
        if (jsonStr.startsWith("{")) {
            return new JSONObject(jsonStr);
        } else if (jsonStr.startsWith("[")) {
            JSONArray jsonArray = new JSONArray(jsonStr);
            if (jsonArray.length() > 0 && jsonArray.get(0) instanceof JSONObject) {
                return getJsonObject(jsonArray);
            }
        }
        return null;

    }

    /**
     * 过滤掉// 和/** 注释
     */
    public String removeComment(String str) {
        String temp = str.replaceAll("/\\*" +
                "[\\S\\s]*?" +
                "\\*/", "");
        return temp.replaceAll("//[\\S\\s]*?\n", "");
    }

    private void handleDataError(Exception e2) {
        e2.printStackTrace();
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e2.printStackTrace(printWriter);
        printWriter.close();
        // TODO: handle error
    }

    private List<String> collectGenerateFiled(JSONObject json) {
        Set<String> keySet = json.keySet();
        return new ArrayList<>(keySet);
    }

    private void parseJson(JSONObject json) {
        List<String> generateFiled = collectGenerateFiled(json);
        handleNormal(json, generateFiled);
    }

    private void handleNormal(JSONObject json, List<String> generateFiled) {
        WriteCommandAction.runWriteCommandAction(project, () -> {

            Document document = editor.getDocument();
            // 获取光标所在位置
            int curOffset = editor.getCaretModel().getOffset();
            //清除内容
            //document.deleteString(0, document.getTextLength());
            //生成Ts实体,并插入到光标所在位置
            document.insertString(curOffset, Json2ts.createCommentString(json, generateFiled, psiFile));
            Toast.make(this.project, MessageType.INFO, "success");
        });
     }
}
