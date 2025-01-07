package com.jifs.server.controller;

import com.jifs.server.common.exception.AccountException;
import com.jifs.server.dto.AccountDto;
import com.jifs.server.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, path = "/create")
    public ResponseEntity<String> createAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(authService.createAccount(accountDto),HttpStatus.CREATED);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> login(@RequestBody AccountDto accountDto) {
        try {
            return new ResponseEntity<>(authService.login(accountDto),HttpStatus.OK);
        } catch (AccountException ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
