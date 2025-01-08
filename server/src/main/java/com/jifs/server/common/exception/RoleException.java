package com.jifs.server.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoleException extends RuntimeException {
    public RoleException(String message) {
        super(message);
        log.error(message);
    }
}
