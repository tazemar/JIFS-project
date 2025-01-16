package com.jifs.server.common.mapper;

import com.jifs.server.dto.AccountDto;
import com.jifs.server.dto.response.AccountResponseDto;
import com.jifs.server.entity.Account;
import com.jifs.server.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    Account dtoToAccount(AccountDto accountDto);

    AccountResponseDto AccountToAccountResponseDto(Account account);

    @Mappings({
            @Mapping(source = "role", target = "role", qualifiedByName = "roleToString")
    })
    List<AccountResponseDto> accountsToAccountResponseDtos(List<Account> accounts);

    default String mapRoleToString(Role role) {
        if (role != null) {
            return role.getName().name();
        }
        return null;
    }
}
