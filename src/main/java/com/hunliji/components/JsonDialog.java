package com.hunliji.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hunliji.ConvertBridge;
import com.hunliji.pojo.ReqQuery;
import com.hunliji.pojo.ReqResponse;
import com.hunliji.pojo.YapiResponse;
import com.hunliji.utils.JSONObject;
import com.hunliji.utils.SchemeHandler;
import com.hunliji.utils.YapiRequest;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;

import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonDialog extends JFrame {
    private Project project;
    private Editor editor;
    private JTextField linkLTextField;
    private JFrame frame;

    public JsonDialog(Project project, Editor editor) {
        this.project = project;
        this.editor = editor;
    }

    private void onCancel() {
        dispose();
    }


    public void show() {
        // 创建 JFrame 实例
        this.frame = new JFrame("yapi2ts");
        frame.setSize(500, 200);
        frame.setLocationRelativeTo(frame.getOwner());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* 创建面板
         * 我们可以创建多个面板并在 JFrame 中指定位置
         * 面板中我们可以添加文本字段，按钮及其他组件。
         */
        JPanel panel = new JPanel();
        // 添加面板
        frame.add(panel);
        /*
         * 调用用户定义的方法并添加组件到面板
         */
        placeComponents(panel);

        // 设置界面可见
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {

        /*
         * 这边设置布局为 null
         */
        panel.setLayout(null);

        // 创建 JLabel
        JLabel userLabel = new JLabel("link url:");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        userLabel.setBounds(10, 20, 50, 25);
        panel.add(userLabel);

        /*
         * 创建文本域用于用户输入
         */
        linkLTextField = new JTextField(20);
        linkLTextField.setBounds(80, 20, 250, 25);
        panel.add(linkLTextField);

        // 生成响应体types
        JButton typesButton = new JButton("生成响应体类型");
        typesButton.setBounds(10, 80, 180, 35);
        typesButton.addActionListener(e -> onTransformTypes("res"));
        panel.add(typesButton);

        // 生成请求体types
        JButton codesButton = new JButton("生成请求体类型");
        codesButton.setBounds(10, 120, 200, 35);
        codesButton.addActionListener(e -> onTransformTypes("req"));
        panel.add(codesButton);

        // TODO: 下个版本支持一键生成axios请求代码
        // 生成axios请求代码
        //JButton codesButton = new JButton("generate request code");
        //codesButton.setBounds(10, 120, 200, 35);
        //codesButton.addActionListener(e -> onTransformAxiosRequestCodes());
        //panel.add(codesButton);
    }

    /**
     * 生成ts类型
     */
    private void onTransformTypes(String type) {
        String url = linkLTextField.getText();

        if (url.equals("")) {
            Toast.make(this.project, MessageType.WARNING, "url cannot be empty!");
            return;
        }
        Pattern pattern = Pattern.compile("project\\/(\\d+)\\/interface\\/api\\/(\\d+)");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            String projectId = matcher.group(1);
            String id = matcher.group(2);
            String jsonStr = YapiRequest.getInterfaceData(id, projectId);
            ObjectMapper mapper = new ObjectMapper();
            try {
                if (type.equals("res")) {
                    YapiResponse yapiResponse = null;
                    // 字符串转对象
                    yapiResponse = mapper.readValue(jsonStr, YapiResponse.class);
                    String resSchemaStr = yapiResponse.getData().getResBody();
                    String responseStr = new SchemeHandler().parseYapiSchema(resSchemaStr);
                    ConvertBridge convertBridge = new ConvertBridge(responseStr, this.project, this.editor);
                    convertBridge.run();
                } else {
                    ReqResponse yapiResponse = null;
                    // 字符串转对象
                    yapiResponse = mapper.readValue(jsonStr, ReqResponse.class);
                    if (yapiResponse.getData().getMethod().equalsIgnoreCase("get")) {
                        List<ReqQuery> reqQueryList = yapiResponse.getData().getReqQuery();
                        Map<String, String> map = new HashMap<>();
                        reqQueryList.forEach(reqQuery -> {
                            map.put(reqQuery.getName(), reqQuery.getDesc());
                        });
                        String reqQueryJsonStr = JSONObject.valueToString(map);
                        ConvertBridge convertBridge = new ConvertBridge(reqQueryJsonStr, this.project, this.editor);
                        convertBridge.run();
                    } else {
                        String reqSchemaStr = yapiResponse.getData().getReqBodyOther();
                        String reqResponseStr = new SchemeHandler().parseYapiSchema(reqSchemaStr);
                        ConvertBridge convertBridge = new ConvertBridge(reqResponseStr, this.project, this.editor);
                        convertBridge.run();
                    }
                }
                this.frame.setVisible(false);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ScriptException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 生成axios请求代码
     */
    private void onTransformAxiosRequestCodes() {
        Toast.make(this.project, MessageType.INFO, "yapi转axios请求代码");
    }

}