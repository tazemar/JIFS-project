package com.jifs.server.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class IdDto {
    @NotBlank(message = "id cannot be blank")
    private UUID id;
}
