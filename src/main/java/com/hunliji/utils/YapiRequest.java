package com.hunliji.utils;

import com.hunliji.config.ConfigPersistence;
import com.hunliji.dto.ConfigDTO;
import java.util.List;

/**
 * yapi请求列表类
 */
public class YapiRequest {
    public static String getInterfaceData(String id, String projectId) {
        ConfigPersistence configPersistence = ConfigPersistence.getInstance();
        List<ConfigDTO> configDTOS = configPersistence.getConfigs();
        var ref = new Object() {
            String token = null;
        };
        configDTOS.forEach(configDTO -> {
            if (configDTO.getProjectId() == Integer.parseInt(projectId)) {
                ref.token = configDTO.getToken();
            }
        });
        String token = ref.token;
        String url = "http://api.hljnbw.cn/api/interface/get?id=" + id + "&token=" + token;
        String jsonStr = HttpClient.doGet(url);
        return jsonStr;
    }
    public static String getTypeList(String projectId, String apiId) {
        String url = "http://api.hljnbw.cn/api/data/type/http/list?projectId=" + projectId + "&resourceId=" + apiId;
        String jsonStr = HttpClient.doGet(url);
        return jsonStr;
    }
}
