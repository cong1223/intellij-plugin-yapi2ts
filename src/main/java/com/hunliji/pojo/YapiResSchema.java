package com.hunliji.pojo;

import com.hunliji.constants.SchemaTypeEnum;

import java.util.Map;

/**
 * @author 小聪忙
 * @date 2021/11/1 7:20 下午
 * @desc none
 */
public class YapiResSchema {
    private String $schema;
    private Map<String, Object> properties;
    private Map<String, Object> items;
    private SchemaTypeEnum type;

    public String get$schema() {
        return $schema;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public Map<String, Object> getItems() {
        return items;
    }

    public SchemaTypeEnum getType() {
        return type;
    }
}
