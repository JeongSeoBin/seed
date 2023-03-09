package com.innowireless.web.security;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class CustomAuthenticationException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    private final int code;

    public CustomAuthenticationException(int code) {
        super("");
        this.code = code;
    }

    public CustomAuthenticationException(int code, String message) {
        super(message);
        this.code = code;
    }
}
