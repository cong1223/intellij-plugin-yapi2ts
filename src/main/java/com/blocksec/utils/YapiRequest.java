package com.blocksec.utils;

/**
 * yapi请求列表类
 */
public class YapiRequest {
    public static String getInterfaceData(String id, String token) {
        String url = "http://192.168.2.201:3000/api/interface/get?id=" + id + "&token=" + token;
        String jsonStr = HttpClient.doGet(url);
        return jsonStr;
    }
    public static String getTypeList(String projectId, String apiId) {
        String url = "http://192.168.2.201:3000/api/data/type/http/list?projectId=" + projectId + "&resourceId=" + apiId;
        String jsonStr = HttpClient.doGet(url);
        return jsonStr;
    }
}
