package com.jifs.server.service;

import com.jifs.server.common.exception.AccountException;
import com.jifs.server.dto.AccountDto;
import com.jifs.server.dto.IdDto;

import java.util.List;

public interface AccountService {
    String createAccount(AccountDto accountDto) throws AccountException;

    String login(AccountDto accountDto) throws AccountException;

    List<AccountDto> allUsers();

    List<AccountDto> allAccounts();

    String promoteAdministrator(IdDto id);
}
