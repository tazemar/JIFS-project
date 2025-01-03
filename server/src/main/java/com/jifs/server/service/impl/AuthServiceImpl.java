package com.jifs.server.service.impl;

import com.jifs.server.dto.AccountDto;
import com.jifs.server.entity.Account;
import com.jifs.server.repository.AccountRepository;
import com.jifs.server.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private AccountRepository accountRepository;

    @Override
    public void createAccount(AccountDto accountDto) {
        Account account = new Account();
        accountRepository.save(new Account());

    }

    @Override
    public void login(AccountDto accountDto) {

    }
}
