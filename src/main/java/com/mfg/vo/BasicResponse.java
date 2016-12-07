package com.mfg.vo;

/**
 * Created by lw on 16/12/7.
 */
public class BasicResponse<T> {
    public static final int RESPONSE_CODE_SUCCESS = 0;
    public static final int RESPONSE_CODE_FAIL = 1;
    public static final int RESPONSE_CODE_ERROR = 2;

    private int code;
    private String message;
    private T data;
    public BasicResponse() {
    }

    private BasicResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private BasicResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> BasicResponse<?> success(T data){
        return new BasicResponse<T>(RESPONSE_CODE_SUCCESS,"ok",data);
    }

    public static <T> BasicResponse<?> fail(String msg){
        return new BasicResponse<T>(RESPONSE_CODE_FAIL,msg);
    }
    public static <T> BasicResponse<?> error(String msg){
        return new BasicResponse<T>(RESPONSE_CODE_ERROR,msg);
    }
}
