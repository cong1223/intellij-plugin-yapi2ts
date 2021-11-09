package com.hunliji.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author 小聪忙
 * @date 2021/11/1 4:43 下午
 * @desc none
 */
public class ResponseData {
    @JsonProperty("query_path")
    private QueryPath queryPath;
    @JsonProperty("edit_uid")
    private int editUid;
    private String status;
    private String type;
    @JsonProperty("req_body_is_json_schema")
    private boolean reqBodyIsJsonSchema;
    @JsonProperty("res_body_is_json_schema")
    private boolean resBodyIsJsonSchema;
    @JsonProperty("api_opened")
    private boolean apiOpened;
    private int index;
    private List<String> tag;
    @JsonProperty("_id")
    private int id;
    @JsonProperty("res_body")
    private String resBody;
    private String method;
    @JsonProperty("res_body_type")
    private String resBodyType;
    private String title;
    private String path;
    private int catid;
    private String markdown;
    @JsonProperty("req_headers")
    private List<String> reqHeaders;
    @JsonProperty("req_query")
    private List<ReqQuery> reqQuery;
    private String desc;
    @JsonProperty("project_id")
    private int projectId;
    @JsonProperty("req_params")
    private List<String> reqParams;
    private int uid;
    @JsonProperty("add_time")
    private int addTime;
    @JsonProperty("up_time")
    private int upTime;
    @JsonProperty("req_body_form")
    private List<String> reqBodyForm;
    @JsonProperty("__v")
    private int v;
    private String username;

    public void setQueryPath(QueryPath queryPath) {
        this.queryPath = queryPath;
    }
    public QueryPath getQueryPath() {
        return this.queryPath;
    }
    public void setEditUid(int editUid) {
        this.editUid = editUid;
    }
    public int getEditUid() {
        return this.editUid;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return this.type;
    }
    public void setReqBodyIsJsonSchema(boolean reqBodyIsJsonSchema) {
        this.reqBodyIsJsonSchema = reqBodyIsJsonSchema;
    }
    public boolean getReqBodyIsJsonSchema() {
        return this.reqBodyIsJsonSchema;
    }
    public void setResBodyIsJsonSchema(boolean resBodyIsJsonSchema) {
        this.resBodyIsJsonSchema = resBodyIsJsonSchema;
    }
    public boolean getResBodyIsJsonSchema() {
        return this.resBodyIsJsonSchema;
    }
    public void setApiOpened(boolean apiOpened) {
        this.apiOpened = apiOpened;
    }
    public boolean getApiOpened() {
        return this.apiOpened;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public int getIndex() {
        return this.index;
    }
    public void setTag(List<String> tag) {
        this.tag = tag;
    }
    public List<String> getTag() {
        return this.tag;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }
    public void setResBody(String resBody) {
        this.resBody = resBody;
    }
    public String getResBody() {
        return this.resBody;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getMethod() {
        return this.method;
    }
    public void setResBodyType(String resBodyType) {
        this.resBodyType = resBodyType;
    }
    public String getResBodyType() {
        return this.resBodyType;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getPath() {
        return this.path;
    }
    public void setCatid(int catid) {
        this.catid = catid;
    }
    public int getCatid() {
        return this.catid;
    }
    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }
    public String getMarkdown() {
        return this.markdown;
    }
    public void setReqHeaders(List<String> reqHeaders) {
        this.reqHeaders = reqHeaders;
    }
    public List<String> getReqHeaders() {
        return this.reqHeaders;
    }
    public void setReqQuery(List<ReqQuery> reqQuery) {
        this.reqQuery = reqQuery;
    }
    public List<ReqQuery> getReqQuery() {
        return this.reqQuery;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getDesc() {
        return this.desc;
    }
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    public int getProjectId() {
        return this.projectId;
    }
    public void setReqParams(List<String> reqParams) {
        this.reqParams = reqParams;
    }
    public List<String> getReqParams() {
        return this.reqParams;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public int getUid() {
        return this.uid;
    }
    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }
    public int getAddTime() {
        return this.addTime;
    }
    public void setUpTime(int upTime) {
        this.upTime = upTime;
    }
    public int getUpTime() {
        return this.upTime;
    }
    public void setReqBodyForm(List<String> reqBodyForm) {
        this.reqBodyForm = reqBodyForm;
    }
    public List<String> getReqBodyForm() {
        return this.reqBodyForm;
    }
    public void setV(int v) {
        this.v = v;
    }
    public int getV() {
        return this.v;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return this.username;
    }
}