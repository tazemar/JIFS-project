package com.jifs.server.controller;

import com.jifs.server.common.exception.AccountException;
import com.jifs.server.common.exception.AppError;
import com.jifs.server.dto.AccountDto;
import com.jifs.server.service.AuthService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Object> createAccount(@RequestBody @Valid AccountDto accountDto) {
        try {
            return new ResponseEntity<>(authService.createAccount(accountDto), HttpStatus.CREATED);
        } catch (AccountException ex) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST, ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> login(@RequestBody AccountDto accountDto) {
        try {
            return new ResponseEntity<>(authService.login(accountDto),HttpStatus.OK);
        } catch (AccountException ex) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST, ex.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
