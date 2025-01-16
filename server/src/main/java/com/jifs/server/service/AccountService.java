package com.jifs.server.service;

import com.jifs.server.common.exception.AccountException;
import com.jifs.server.dto.AccountDto;
import com.jifs.server.dto.response.AccountResponseDto;
import com.jifs.server.dto.IdDto;
import com.jifs.server.dto.response.LoginResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface AccountService {
    String createAccount(AccountDto accountDto) throws AccountException;

    LoginResponseDto login(AccountDto accountDto) throws AccountException;

    List<AccountResponseDto> allUsers();

    List<AccountResponseDto> allAccounts();

    String promoteAdministrator(IdDto id);

    AccountResponseDto getAccount(IdDto id);

    String updateAccount(AccountDto accountDto);
}
