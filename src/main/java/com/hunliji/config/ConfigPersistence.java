package com.hunliji.config;

import com.hunliji.constants.DefaultAuthTokenConstant;
import com.hunliji.dto.ConfigDTO;
import com.hunliji.utils.ReflexObjectUtil;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 配置持久化
 */
@State(name = "yapi2ts", storages = {@Storage(value = "$APP_CONFIG$/yapi2ts.xml")})
public class ConfigPersistence implements PersistentStateComponent<List<ConfigDTO>> {
    private List<ConfigDTO> configs = new ArrayList<>();
    private static ConfigPersistence instance = null;

    // 默认配置
    public ConfigPersistence() {
        Field[] fields = new Field[0];
        try {
            fields = ReflexObjectUtil.getAllFields(DefaultAuthTokenConstant.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Field f : fields) {
            Class clazz = null;
            try {
                clazz = Class.forName(DefaultAuthTokenConstant.class.getName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Object o = null;
            try {
                o = clazz.getConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            ConfigDTO configDTO = (ConfigDTO) ReflexObjectUtil.getKeyAndValue(o).get(f.getName());
            this.configs.add(configDTO);
        }
    }

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
