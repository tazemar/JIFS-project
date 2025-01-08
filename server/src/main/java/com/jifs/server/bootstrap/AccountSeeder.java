package com.jifs.server.bootstrap;

import com.jifs.server.common.enums.RoleEnum;
import com.jifs.server.common.mapper.AccountMapper;
import com.jifs.server.dto.AccountDto;
import com.jifs.server.entity.Account;
import com.jifs.server.entity.Role;
import com.jifs.server.repository.AccountRepository;
import com.jifs.server.repository.RoleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Order(2)
public class AccountSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    private final AccountMapper accountMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public AccountSeeder(AccountRepository accountRepository, RoleRepository roleRepository, AccountMapper accountMapper, BCryptPasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.createSuperAdministrator();
    }

    private void createSuperAdministrator() {
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername("root");
        accountDto.setPassword(passwordEncoder.encode("admin123"));
        accountDto.setEmail("root@email.com");

        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.SUPER_ADMIN);
        Optional<Account> optionalUser = accountRepository.findAccountByEmail(accountDto.getEmail());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }
        Account account = accountMapper.dtoToAccount(accountDto);
        account.setRole(optionalRole.get());
        accountRepository.save(account);
    }
}
