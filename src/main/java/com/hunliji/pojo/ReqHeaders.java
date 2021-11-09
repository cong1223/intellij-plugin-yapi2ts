package com.hunliji.pojo;

/**
 * @author 小聪忙
 * @date 2021/11/8 9:38 下午
 * @desc none
 */
/**
 * Copyright 2021 lzltool.com
 */

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Auto-generated: 2021-11-08 21:37:03
 *
 * @author lzltool.com
 * @website http://www.lzltool.com/JsonToJava
 */

public class ReqHeaders {

    private String required;
    @JsonProperty("_id")
    private String id;
    private String name;
    private String value;
    private String example;

    public void setRequired(String required) {
        this.required = required;
    }
    public String getRequired() {
        return this.required;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return this.id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }
    public void setExample(String example) {
        this.example = example;
    }
    public String getExample() {
        return this.example;
    }
}
