package com.jifs.server.controller;

import com.jifs.server.dto.AccountDto;
import com.jifs.server.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity login(@RequestBody AccountDto accountDto) {
        authService.login(accountDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity createAccount(@RequestBody AccountDto accountDto) {
        authService.createAccount(accountDto);
        return ResponseEntity.ok().build();
    }
}
