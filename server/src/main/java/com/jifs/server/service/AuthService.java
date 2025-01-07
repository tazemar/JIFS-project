package com.jifs.server.service;

import com.jifs.server.common.exception.AccountException;
import com.jifs.server.dto.AccountDto;

public interface AuthService {
    String createAccount(AccountDto accountDto) throws AccountException;

    String login(AccountDto accountDto) throws AccountException;
}
