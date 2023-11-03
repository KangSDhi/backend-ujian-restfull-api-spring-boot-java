package com.kangsdhi.backendujianrestfullapispringbootjava.model;

public class Response<T> {

    private Integer status_code;

    private String message;
    private T data;

    public Response(Integer status_code, String message, T data){
        this.status_code = status_code;
        this.message = message;
        this.data = data;
    }

    public Integer getStatus_code() {
        return status_code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
