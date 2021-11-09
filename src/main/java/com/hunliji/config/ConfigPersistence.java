package com.hunliji.config;

import com.hunliji.components.Toast;
import com.hunliji.dto.ConfigDTO;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置持久化
 */
@State(name = "yapi2ts", storages = {@Storage(value = "$APP_CONFIG$/yapi2ts.xml")})
public class ConfigPersistence implements PersistentStateComponent<List<ConfigDTO>> {
    private List<ConfigDTO> configs = new ArrayList<>();
    private static ConfigPersistence instance = null;

    public List<ConfigDTO> getConfigs() {
        return configs;
    }

    public void setConfigs(List<ConfigDTO> configs) {
        this.configs = configs;
    }

    public static ConfigPersistence getInstance(){
        if (null == instance) {
            instance = new ConfigPersistence();
        }
        return instance;
    }

    public String getTokenByProjectId(Project project, Integer projectId) throws Exception {
        List<ConfigDTO> configDTOS = this.getConfigs();
        var ref = new Object() {
            String token = null;
        };
        configDTOS.forEach(configDTO -> {
            if (configDTO.getProjectId() == projectId) {
                ref.token = configDTO.getToken();
            }
        });
        String token = ref.token;
        if (token == null) {
            Toast.make(project, MessageType.ERROR, "没有找到此项目对应的token,请配置后重试!");
            throw new Exception("没有找到此项目对应的token,请配置后重试!");
        }
        return token;
    }

    @Nullable
    @Override
    public List<ConfigDTO> getState() {
        return this.configs;
    }

    @Override
    public void loadState(@NotNull List<ConfigDTO> element) {
        this.configs = element;
    }
}
