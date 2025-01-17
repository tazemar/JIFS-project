package com.jifs.server.dto;


import com.jifs.server.dto.group.CreateGroup;
import com.jifs.server.dto.group.UpdateGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AccountDto {
    @NotNull(message = "Id is mandatory", groups = {UpdateGroup.class})
    private UUID id;

    @NotNull(message = "Username cannot be null", groups = {CreateGroup.class})
    private String username;

    @NotBlank(message = "Password cannot be blank", groups = {CreateGroup.class})
    private String password;

    @NotBlank(message = "Email cannot be null", groups = {CreateGroup.class})
    @Email(message = "Email should be valid", groups = {CreateGroup.class, UpdateGroup.class})
    private String email;
}
