package com.jifs.server.common.mapper;

import com.jifs.server.dto.AccountDto;
import com.jifs.server.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account dtoToAccount(AccountDto accountDto);

    List<AccountDto> accountsToAccountDtos(List<Account> accounts);
}
