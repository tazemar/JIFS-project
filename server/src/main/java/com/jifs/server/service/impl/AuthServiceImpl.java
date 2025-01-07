package com.jifs.server.service.impl;

import com.jifs.server.common.enums.Role;
import com.jifs.server.common.exception.AccountException;
import com.jifs.server.common.mapper.AccountMapper;
import com.jifs.server.dto.AccountDto;
import com.jifs.server.entity.Account;
import com.jifs.server.repository.AccountRepository;
import com.jifs.server.service.AuthService;
import com.jifs.server.config.security.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public AuthServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, JWTService jwtService, BCryptPasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String createAccount(AccountDto accountDto) throws AccountException {
        log.info("Create account");
        if (accountRepository.existsByEmail(accountDto.getEmail())) {
            log.error("Email already exists");
            throw new AccountException("Email already in use");
        }
        Account account = accountMapper.dtoToAccount(accountDto);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRole(Role.USER);
        accountRepository.save(account);
        log.info("Account created : {}", account.getId());
        return account.getEmail();
    }

    @Override
    public String login(AccountDto accountDto) throws AccountException {
        log.info("Login account : {}", accountDto.getEmail());
        Optional<Account> accountOptional = accountRepository.findAccountByEmail(accountDto.getEmail());
        if (accountOptional.isEmpty()) {
            log.error("Account not found with email");
            throw new AccountException("Account not found with email");
        }
        Account account = accountOptional.get();
        if (!passwordEncoder.matches(accountDto.getPassword(), account.getPassword())) {
            log.error("Password not match");
            throw new AccountException("Password not match");
        }
        return jwtService.generateToken(account.getEmail());
    }
}
