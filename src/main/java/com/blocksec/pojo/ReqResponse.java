/**
 * Copyright 2021 json.cn
 */
package com.blocksec.pojo;


public class ReqResponse {

    private int errcode;
    private String errmsg;
    private ReqData data;
    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }
    public int getErrcode() {
        return errcode;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
    public String getErrmsg() {
        return errmsg;
    }

    public void setData(ReqData data) {
        this.data = data;
    }
    public ReqData getData() {
        return data;
    }

}