package com.hunliji.pojo;

import java.util.List;

/**
 * @author 小聪忙
 * @date 2021/11/1 4:41 下午
 * @desc none
 */
class QueryPath {

    private String path;
    private List<String> params;

    public void setPath(String path) {
        this.path = path;
    }
    public String getPath() {
        return this.path;
    }
    public void setParams(List<String> params) {
        this.params = params;
    }
    public List<String> getParams() {
        return this.params;
    }
}
