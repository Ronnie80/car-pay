package com.yangyl.manage.common.dto;



/**
 * Created by Cangrong on 2018/2/6.
 * API 返回的实体类型
 */
public class Response<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> Response<T> ok() {
        Response<T> response = new Response<T>();
        response.setCode(HttpCode.SUCCESS.getCode());
        return response;
    }

    public static <T> Response<T> ok(T t) {
        Response<T> response = new Response<T>();
        response.setCode(HttpCode.SUCCESS.getCode());
        response.setData(t);
        return response;
    }

    public static Response<String> err(String msg) {
        Response<String> response = new Response<String>();
        response.setCode(HttpCode.SERVER_ERROR.getCode());
        response.setMsg(msg);
        return response;
    }

    public static Response<String> err(int code ,String msg){
        Response<String> response = new Response<String>();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    public Response(int code, String result, T data) {
        this.code = code;
        this.msg = result;
        this.data = data;
    }

    private Response() {
        this.code = HttpCode.SUCCESS.getCode();
        this.msg = null;
        this.data = null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
