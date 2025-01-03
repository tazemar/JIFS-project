package com.jifs.server.common.mapper;

import com.jifs.server.dto.AccountDto;
import com.jifs.server.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto accountToDto(Account account);

    Account dtoToAccount(AccountDto accountDto);
}
