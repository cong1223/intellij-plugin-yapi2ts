package com.blocksec.config;

import com.blocksec.components.Toast;
import com.blocksec.dto.ConfigDTO;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 配置持久化
 */
@State(name = "yapi2ts", storages = {@Storage(value = "yapi2ts.xml")})
public class ConfigPersistence implements PersistentStateComponent<MyPersistenceConfiguration> {

    private MyPersistenceConfiguration config;

    @Nullable
    @Override
    public MyPersistenceConfiguration getState() {
        if (config == null) {
            config = new MyPersistenceConfiguration();
            List<ConfigDTO> configDTOS = new ArrayList<>();
            config.setConfigs(configDTOS);
        }
        return config;
    }

    public static ConfigPersistence getInstance() {
        return ApplicationManager.getApplication().getService(ConfigPersistence.class);
    }

    @Override
    public void loadState(@NotNull MyPersistenceConfiguration state) {
        XmlSerializerUtil.copyBean(state, Objects.requireNonNull(getState()));
    }

    public String getTokenByProjectId(Project project, Integer projectId) throws Exception {
        List<ConfigDTO> configDTOS = config.getConfigs();
        var ref = new Object() {
            String token = null;
        };
        configDTOS.forEach(configDTO -> {
            if (configDTO.getProjectId().equals(projectId)) {
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
}
