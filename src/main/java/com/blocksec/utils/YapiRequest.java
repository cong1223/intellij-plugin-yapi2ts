package com.blocksec.utils;

/**
 * yapi请求列表类
 */
public class YapiRequest {
    public static String getInterfaceData(String domain, String id, String token) {
        String url = domain + "/api/interface/get?id=" + id + "&token=" + token;
        return HttpClient.doGet(url);
    }
}
