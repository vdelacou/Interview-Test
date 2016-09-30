package com.mfg.core;

/**
 * ^_^
 * Created by gaojiawei on 2016/9/30.
 */
public class ApiEntity<T> {
    private long timestamp = System.currentTimeMillis();
    private int status;
    private String message;
    private T body;

    public ApiEntity() {
        this.status = ApiStatus.STATUS_200.getCode();
        this.message = ApiStatus.STATUS_200.getMessage();
    }

    public ApiEntity(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiEntity(ApiStatus status) {
        this.status = status.getCode();
        this.message = status.getMessage();
    }

    public ApiEntity(T body) {
        this.status = ApiStatus.STATUS_200.getCode();
        this.message = ApiStatus.STATUS_200.getMessage();
        this.body = body;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return this.body;
    }

    public void setBody(T body) {
        this.body = body;
    }


}
