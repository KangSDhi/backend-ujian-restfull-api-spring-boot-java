package com.kangsdhi.backendujianrestfullapispringbootjava.app.advice;

import io.jsonwebtoken.MalformedJwtException;

public class AdviceTokenFormatInvalid extends MalformedJwtException {
    public AdviceTokenFormatInvalid(String message) {
        super(message);
    }

    public AdviceTokenFormatInvalid(String message, Throwable cause) {
        super(message, cause);
    }
}
