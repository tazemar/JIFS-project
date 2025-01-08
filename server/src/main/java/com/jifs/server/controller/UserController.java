package com.jifs.server.controller;

import com.jifs.server.dto.AccountDto;
import com.jifs.server.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private AccountService accountService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, path = "/create")
    public ResponseEntity<String> createAccount(@RequestBody @Valid AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> login(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.login(accountDto),HttpStatus.OK);
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<List<AccountDto>> allUsers() {
        return new ResponseEntity<>(accountService.allUsers(),HttpStatus.OK);
    }
}
