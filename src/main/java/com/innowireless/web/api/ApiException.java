package com.innowireless.web.api;

public class ApiException extends RuntimeException {

    public final int code;

    public ApiException(int code) {
        this.code = code;
    }

    public ApiException(int code, String message) {
        super(message);
        this.code = code;
    }
}
