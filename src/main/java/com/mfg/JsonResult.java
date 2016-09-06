package com.mfg;

import java.io.Serializable;

/**
 * @author jeffrey on 9/6/16.
 * @project interview
 */
public class JsonResult implements Serializable {
    private String msg;
    private int statusCode;
    private Object result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
