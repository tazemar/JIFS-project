package com.jifs.server.service;

import com.jifs.server.dto.AccountDto;

public interface AuthService {
    void login(AccountDto accountDto);

    void createAccount(AccountDto accountDto);
}
