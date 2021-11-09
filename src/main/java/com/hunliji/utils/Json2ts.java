package com.hunliji.utils;
import com.intellij.psi.PsiFile;
import java.util.*;
import static com.hunliji.utils.StringUtils.captureStringLeaveUnderscore;


public class Json2ts {
    /**
     * 默认空格
     */
    public static String defaultSpace = " ";
    /**
     * 分割行
     */
    public static String lineSeparator = "\n";
    /**
     * 注释：记录已经被转换的JSON实体
     */
    private static Map<String, JSONObject> jsonObjectMap = new HashMap<>();

    /**
     * 注释：创建返回TS实体
     *
     * @param json
     * @param filedList
     * @param psiFile
     * @return
     */
    public static String createCommentString(JSONObject json, List<String> filedList, PsiFile psiFile) {
        List<String> objectKeys = new ArrayList<>();
        List<String> arrayKeys = new ArrayList<>();
        jsonObjectMap.clear();
        StringBuilder stringBuilder = new StringBuilder();
        String fileName = captureStringLeaveUnderscore(getPsiClassName(psiFile));
        stringBuilder.append("export default interface ").append(fileName).append(" {");
        stringBuilder.append(lineSeparator);
        for (String key : filedList) {
            //记录值为数组或者对象的key
            if (json.get(key) instanceof JSONObject) {
                JSONObject jsonObject = json.getJSONObject(key);
                //if (jsonObject.length() != 0) {
                    objectKeys.add(key);
                //}
            } else if (json.get(key) instanceof JSONArray) {
                JSONArray jsonArray = json.getJSONArray(key);
                if (jsonArray.length() != 0 && jsonArray.get(0) instanceof JSONObject) {
                    arrayKeys.add(key);
                }
            }
            stringBuilder.append(getAttributesByKey(json, key));
        }
        stringBuilder.append("}");
        //如果有JSONObject对象转换为对应的TS对象
        for (String objectKey : objectKeys) {
            stringBuilder.append(createObjectString(json.getJSONObject(objectKey), objectKey));
        }
        //如果有JSONArray并且数组对象为JSONObject对象
        for (String arrayKey : arrayKeys) {
            stringBuilder.append(createObjectString(json.getJSONArray(arrayKey).getJSONObject(0), arrayKey));
        }
        return stringBuilder.toString();
    }

    /**
     * 注释：判断2个JSONObject结构是否一致
     *
     * @param jsonA
     * @param jsonB
     * @return
     */
    public static boolean isEqualJsonObject(JSONObject jsonA, JSONObject jsonB) {
        return jsonA.keySet().toString().equals(jsonB.keySet().toString());
    }

    /**
     * 注释：创建JSONObject对应的实体
     *
     * @param json
     * @param ObjectKey
     * @return
     */
    public static String createObjectString(JSONObject json, String ObjectKey) {
        //判断创建对象是否重复
        if (jsonObjectMap.containsKey(ObjectKey)) {
            //比较对象的key是否完全一致，完全一致则认为是同一对象
            JSONObject alreadyJson = jsonObjectMap.get(ObjectKey);
            if (isEqualJsonObject(alreadyJson, json)) {
                return "";
            }
        }
        jsonObjectMap.put(ObjectKey, json);
        List<String> objectKeys = new ArrayList<>();
        List<String> arrayKeys = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(StringUtils.repeatStr(lineSeparator, 2));
        stringBuilder.append("export interface ").append(captureStringLeaveUnderscore(ObjectKey)).append(" {");
        stringBuilder.append(lineSeparator);
        for (String k : collectGenerateFiled(json)) {
            //记录值为数组或者对象的key
            if (json.get(k) instanceof JSONObject) {
                JSONObject jsonObject = json.getJSONObject(k);
                //if (jsonObject.length() != 0) {
                    objectKeys.add(k);
                //}
            } else if (json.get(k) instanceof JSONArray) {
                JSONArray jsonArray = json.getJSONArray(k);
                if (jsonArray.length() != 0 && jsonArray.get(0) instanceof JSONObject) {
                    arrayKeys.add(k);
                }
            }
            stringBuilder.append(getAttributesByKey(json, k));
        }
        stringBuilder.append("}");
        //如果有JSONObject对象转换为对应的TS对象
        for (String objectKey : objectKeys) {
            stringBuilder.append(createObjectString(json.getJSONObject(objectKey), objectKey));
        }
        //如果有JSONArray并且数组对象为JSONObject对象
        for (String arrayKey : arrayKeys) {
            stringBuilder.append(createObjectString(json.getJSONArray(arrayKey).getJSONObject(0), arrayKey));
        }
        return stringBuilder.toString();
    }

    /**
     * 注释：获取当前编译文件类名
     *
     * @param psiFile
     * @return
     */
    private static String getPsiClassName(PsiFile psiFile) {
        return psiFile.getName().replaceAll("\\.ts$", "").replaceAll("\\.js$", "");
    }

    /**
     * 注释：根据Key获取属性
     *
     * @param json
     * @param key
     * @return
     */
    private static String getAttributesByKey(JSONObject json, String key) {
        if (key.equals("eventExtData"))  {
            System.out.println("");
        }
        StringBuilder builder = new StringBuilder();
        Object object = json.get(key);
        if (object instanceof Number) {
            builder.append(StringUtils.repeatStr(defaultSpace, 2));
            builder.append(defaultSpace);
            builder.append(key).append(":");
            builder.append(defaultSpace);
            builder.append("number");
            builder.append(lineSeparator);
        } else if (object instanceof String) {
            builder.append(StringUtils.repeatStr(defaultSpace, 2));
            builder.append(defaultSpace);
            builder.append(key).append(":");
            builder.append(defaultSpace);
            builder.append("string");
            builder.append(lineSeparator);
        } else if (object instanceof Boolean) {
            builder.append(StringUtils.repeatStr(defaultSpace, 2));
            builder.append(defaultSpace);
            builder.append(key).append(":");
            builder.append(defaultSpace);
            builder.append("boolean");
            builder.append(lineSeparator);
        } else if (object instanceof JSONObject) {
            //if (((JSONObject) object).length() != 0) {
                builder.append(StringUtils.repeatStr(defaultSpace, 2));
                builder.append(defaultSpace);
                builder.append(key).append(":");
                builder.append(defaultSpace);
                builder.append(captureStringLeaveUnderscore(key));
                builder.append(lineSeparator);
            //}
        } else if (object instanceof JSONArray) {
            if (((JSONArray) object).length() != 0) {
                Object arrayObject = ((JSONArray) object).get(0);
                if (arrayObject instanceof String) {
                    builder.append(StringUtils.repeatStr(defaultSpace, 2));
                    builder.append(defaultSpace);
                    builder.append(key).append(":");
                    builder.append(defaultSpace);
                    builder.append("string[]");
                } else if (arrayObject instanceof Number) {
                    builder.append(StringUtils.repeatStr(defaultSpace, 2));
                    builder.append(defaultSpace);
                    builder.append(key).append(":");
                    builder.append(defaultSpace);
                    builder.append("number[]");
                } else if (arrayObject instanceof JSONObject) {
                    builder.append(StringUtils.repeatStr(defaultSpace, 2));
                    builder.append(defaultSpace);
                    builder.append(key).append(":");
                    builder.append(defaultSpace);
                    builder.append(captureStringLeaveUnderscore(key)).append("[]");
                }
                builder.append(lineSeparator);
            }
        } else {
            builder.append(StringUtils.repeatStr(defaultSpace, 2));
            builder.append(defaultSpace);
            builder.append(key).append(":");
            builder.append(defaultSpace);
            builder.append("any");
            builder.append(lineSeparator);
        }
        return builder.toString();
    }

    /**
     * 注释：获取Json对象Key的集合
     *
     * @param json
     * @return
     */
    private static List<String> collectGenerateFiled(JSONObject json) {
        Set<String> keySet = json.keySet();
        return new ArrayList<>(keySet);
    }
}
