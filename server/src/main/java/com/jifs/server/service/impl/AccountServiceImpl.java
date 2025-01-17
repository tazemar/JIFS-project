package com.jifs.server.service.impl;

import com.jifs.server.common.enums.RoleEnum;
import com.jifs.server.common.exception.AccountException;
import com.jifs.server.common.exception.RoleException;
import com.jifs.server.common.mapper.AccountMapper;
import com.jifs.server.dto.AccountDto;
import com.jifs.server.dto.response.AccountResponseDto;
import com.jifs.server.dto.IdDto;
import com.jifs.server.dto.response.LoginResponseDto;
import com.jifs.server.entity.Account;
import com.jifs.server.entity.Role;
import com.jifs.server.repository.AccountRepository;
import com.jifs.server.repository.RoleRepository;
import com.jifs.server.service.AccountService;
import com.jifs.server.config.security.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    private final AccountMapper accountMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;



    public AccountServiceImpl(AccountRepository accountRepository, RoleRepository roleRepository, AccountMapper accountMapper, JWTService jwtService, BCryptPasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.accountMapper = accountMapper;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String createAccount(AccountDto accountDto) throws AccountException {
        log.info("AccountService : Create account");
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);
        if (optionalRole.isEmpty()) {
            throw new RoleException("Role does not exist");
        }
        if (accountRepository.existsByEmail(accountDto.getEmail())) {
            throw new AccountException("Email already in use");
        }
        Account account = accountMapper.dtoToAccount(accountDto);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRole(optionalRole.get());
        accountRepository.save(account);
        log.info("AccountService : Account created -> {}", account.getId());
        return account.getEmail();
    }

    @Override
    public LoginResponseDto login(AccountDto accountDto) throws AccountException {
        log.info("AccountService : Login account -> {}", accountDto.getEmail());
        Optional<Account> accountOptional = accountRepository.findAccountByEmail(accountDto.getEmail());
        if (accountOptional.isEmpty()) {
            throw new AccountException("Account not found with email");
        }
        Account account = accountOptional.get();
        if (!passwordEncoder.matches(accountDto.getPassword(), account.getPassword())) {
            throw new AccountException("Password not match");
        }
        return new LoginResponseDto(jwtService.generateToken(account.getEmail()), account.getRole().getName().name(), account.getId(), jwtExpiration);
    }

    @Override
    public AccountResponseDto getAccount(IdDto idDto) {
        log.info("AccountService : Get Account -> {}", idDto.getId());
        Account account = getAccountFunction(idDto);
        return accountMapper.AccountToAccountResponseDto(account);
    }

    @Override
    public String updateAccount(AccountDto accountDto) {
        log.info("AccountService : Update Account -> {}", accountDto.getId());
        Account originAccount = getAccountFunction(new IdDto(accountDto.getId()));

        if (accountDto.getUsername() != null) {
            log.info("AccountService : Update username : {} -> {}", originAccount.getUsername(), accountDto.getUsername());
            originAccount.setUsername(accountDto.getUsername());
        }
        if (accountDto.getEmail() != null && !accountDto.getEmail().equals(originAccount.getEmail())) {
            log.info("AccountService : Update email : {} -> {}", originAccount.getEmail(), accountDto.getEmail());
            originAccount.setEmail(accountDto.getEmail());
        }
        if (accountDto.getPassword() != null) {
            log.info("AccountService : Update password");
            originAccount.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        }
        accountRepository.save(originAccount);
        return "Account updated successfully";
    }

    @Override
    public List<AccountResponseDto> allUsers() {
        log.info("AccountService : Getting all users");
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);
        if (optionalRole.isEmpty()) {
            throw new RoleException("Role does not exist");
        }
        Role userRole = optionalRole.get();
        return accountMapper.accountsToAccountResponseDtos(accountRepository.findByRole(userRole));
    }

    @Override
    public List<AccountResponseDto> allAccounts() {
        log.info("AccountService : Getting all accounts");
        return accountMapper.accountsToAccountResponseDtos(accountRepository.findAll());
    }

    @Override
    public String promoteAdministrator(IdDto idDto) throws AccountException {
        log.info("AccountService : Promote to administrator -> {}", idDto.getId());
        Optional<Account> optionalAccount = accountRepository.findById(idDto.getId());
        if (optionalAccount.isEmpty()) {
            throw new AccountException("Account does not exist");
        }
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);
        if (optionalRole.isEmpty()) {
            throw new RoleException("Role does not exist");
        }

        Account adminAccount = optionalAccount.get();
        adminAccount.setRole(optionalRole.get());
        accountRepository.save(adminAccount);
        return adminAccount.getEmail();
    }

    private Account getAccountFunction(IdDto idDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedEmail = authentication.getName();

        boolean isUser = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_USER"));

        Optional<Account> optionalAccount = accountRepository.findById(idDto.getId());
        if (optionalAccount.isEmpty()) {
            throw new AccountException("Account does not exist");
        }
        Account account = optionalAccount.get();
        if (isUser && !account.getEmail().equals(authenticatedEmail)) {
            throw new AccountException("You can retrieve only your account");
        }
        return account;
    }
}
