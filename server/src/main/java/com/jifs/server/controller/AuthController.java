package com.jifs.server.controller;

import com.jifs.server.dto.AccountDto;
import com.jifs.server.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, path = "/create")
    public ResponseEntity<Object> createAccount(@RequestBody @Valid AccountDto accountDto) {
        return new ResponseEntity<>(authService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> login(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(authService.login(accountDto),HttpStatus.OK);
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<List<AccountDto>> allUsers() {
        return new ResponseEntity<>(authService.allUsers(),HttpStatus.OK);
    }
}
