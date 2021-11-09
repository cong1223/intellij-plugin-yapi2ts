package com.hunliji.dto;

/**
 * 接口文档token配置类
 */
public class ConfigDTO {
    private Integer projectId;
    private String token;
    private String cname;
    public ConfigDTO(Integer projectId, String token, String cname) {
        this.projectId = projectId;
        this.token = token;
        this.cname = cname;
    }

    public String getCname() {
        return cname;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }
        if (object instanceof ConfigDTO) {
            ConfigDTO config = (ConfigDTO) object;
            return this.projectId.equals(config.projectId);
        }
        return super.equals(object);
    }
}
