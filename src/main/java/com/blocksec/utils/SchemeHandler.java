package com.blocksec.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * yapi返回模型数据处理成实际接口返回数据json模型类
 */
public class SchemeHandler {
    /**
     * 暴露方法 获取最终json字符串
     * @param jsonStr
     * @return
     */
    public static String parseYapiSchema(String jsonStr){
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        JSONObject result = handleObject(jsonObject);
        return result.toJSONString();
    }

    /**
     * 处理对象
     * @param object
     * @return
     */
    private static JSONObject handleObject(JSONObject object){
        JSONObject result = new JSONObject();

        String type = object.getString("type");
        JSONObject properties = object.getJSONObject("properties");
        for (String property : properties.keySet()) {
            JSONObject childObject = properties.getJSONObject(property);
            String childType = childObject.getString("type");

            if("object".equals(childType)){
                JSONObject jsonObject = handleObject(childObject);
                result.put(property,jsonObject);
                continue;
            }
            if("array".equals(childType)){
                JSONArray jsonArray = handleArray(childObject);
                result.put(property,jsonArray);
                continue;
            }

            Object o = handleBasicObject(childType);
            result.put(property,o);
        }

        return result;
    }


    /**
     * 处理数组
     * @param object
     * @return
     */
    private static JSONArray handleArray(JSONObject object){
        JSONObject item = object.getJSONObject("items");
        JSONObject jsonObject = handleObject(item);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject);
        return jsonArray;
    }

    /**
     * 转换基本数据类型
     * @param type
     * @return
     */
    private static Object handleBasicObject(String type){
        switch (type){
            case "number" :
            case "integer" : return 1;
            case "boolean" : return false;
            default : return "";
        }
    }
}
