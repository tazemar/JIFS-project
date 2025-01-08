package com.jifs.server.service;

import com.jifs.server.common.exception.AccountException;
import com.jifs.server.dto.AccountDto;

import java.util.List;

public interface AuthService {
    String createAccount(AccountDto accountDto) throws AccountException;

    String login(AccountDto accountDto) throws AccountException;

    List<AccountDto> allUsers();
}
