package com.kangsdhi.backendujianrestfullapispringbootjava.app.util.auth;

public class TokenExpiredException extends Throwable {
    private String message;

    public TokenExpiredException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
