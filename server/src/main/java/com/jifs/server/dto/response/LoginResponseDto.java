package com.jifs.server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseDto {
    private String token;
    private String role;
    private UUID id;
}
