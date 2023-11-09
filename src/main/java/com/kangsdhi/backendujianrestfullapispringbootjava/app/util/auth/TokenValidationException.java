package com.kangsdhi.backendujianrestfullapispringbootjava.app.util.auth;

public class TokenValidationException extends Throwable{
    private String message;

    public TokenValidationException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
