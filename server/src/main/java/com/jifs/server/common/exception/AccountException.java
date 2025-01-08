package com.jifs.server.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountException extends RuntimeException {
    public AccountException(String message) {
        super(message);
        log.error(message);
    }
}
