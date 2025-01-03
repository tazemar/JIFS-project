package com.jifs.server.service.impl;

import com.jifs.server.common.exception.AccountException;
import com.jifs.server.common.mapper.AccountMapper;
import com.jifs.server.dto.AccountDto;
import com.jifs.server.entity.Account;
import com.jifs.server.repository.AccountRepository;
import com.jifs.server.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String createAccount(AccountDto accountDto) {
        Account account = accountMapper.dtoToAccount(accountDto);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return account.getEmail();
    }

    @Override
    public String login(AccountDto accountDto) throws AccountException {
        Optional<Account> accountOptional = accountRepository.findAccountByEmail(accountDto.getEmail());
        if (accountOptional.isEmpty()) {
            throw new AccountException("Account not found with email");
        }
        Account account = accountOptional.get();
        if (!passwordEncoder.matches(accountDto.getPassword(), account.getPassword())) {
            throw new AccountException("Password not match");
        }
        return account.getEmail();
    }
}
