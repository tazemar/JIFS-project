package com.jifs.server.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class AccountResponseDto {
    private UUID id;
    private String username;
    private String email;
    private String role;
    private Date createdAt;
    private Date updatedAt;
}
