package com.player.data.gameaddict.exception;

import org.springframework.security.core.AuthenticationException;

public class ApplicationAuthenticationException extends AuthenticationException {

    public ApplicationAuthenticationException(String msg) {
        super(msg);
    }
}
