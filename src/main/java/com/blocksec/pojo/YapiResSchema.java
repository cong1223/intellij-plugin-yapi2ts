package com.blocksec.pojo;

import com.blocksec.constants.SchemaTypeEnum;

import java.util.Map;

/**
 * @author 小聪忙
 * @date 2021/11/1 7:20 下午
 * @desc none
 */
public class YapiResSchema {
    private String $schema;
    private YapiResSchema properties;
    private YapiResSchema items;
    private SchemaTypeEnum type;

    public String get$schema() {
        return $schema;
    }

    public YapiResSchema getProperties() {
        return properties;
    }

    public YapiResSchema getItems() {
        return items;
    }

    public SchemaTypeEnum getType() {
        return type;
    }
}
