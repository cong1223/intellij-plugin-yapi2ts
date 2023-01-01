package com.blocksec.pojo;

public class YapiResponse {

    private int errcode;
    private String errmsg;
    private ResponseData data;

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }
    public int getErrcode() {
        return this.errcode;
    }
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
    public String getErrmsg() {
        return this.errmsg;
    }
    public void setData(ResponseData data) {
        this.data = data;
    }
    public ResponseData getData() {
        return this.data;
    }
}



