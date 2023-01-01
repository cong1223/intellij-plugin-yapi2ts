package com.blocksec.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 小聪忙
 * @date 2021/11/1 4:42 下午
 * @desc none
 */
public class ReqQuery {

    private String required;
    @JsonProperty("_id")
    private String id;
    private String name;
    private String desc;

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
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getDesc() {
        return this.desc;
    }
}
