package com.kangsdhi.backendujianrestfullapispringbootjava.app.dto.error;

public class ErrorResponse<T> {

    private Integer status_code;

    private String error_message;

    private T data;

    public ErrorResponse(Integer status_code, String error_message, T data){
        this.status_code = status_code;
        this.error_message = error_message;
        this.data = data;
    }

    public Integer getStatus_code() {
        return status_code;
    }

    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
