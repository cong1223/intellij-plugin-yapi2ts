package com.blocksec.config;
import com.blocksec.dto.ConfigDTO;
import java.util.List;

/**
 * 总配置
 *
 */
public class MyPersistenceConfiguration {
    private List<ConfigDTO> configs;

    public List<ConfigDTO> getConfigs() {
        return configs;
    }

    public void setConfigs(List<ConfigDTO> configs) {
        this.configs = configs;
    }
}
