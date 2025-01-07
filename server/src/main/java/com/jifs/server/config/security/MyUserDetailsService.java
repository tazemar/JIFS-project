package com.jifs.server.config.security;

import com.jifs.server.entity.Account;
import com.jifs.server.entity.AccountPrincipal;
import com.jifs.server.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    // Here we use email as identifier and not username
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Account> account = accountRepository.findAccountByEmail(email);

        if (account.isEmpty()) {
            System.out.println("User 404");
            throw new UsernameNotFoundException("User 404");
        }
        return new AccountPrincipal(account.get());
    }
}
