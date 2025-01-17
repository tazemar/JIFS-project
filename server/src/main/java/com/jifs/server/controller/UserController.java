package com.jifs.server.controller;

import com.jifs.server.dto.AccountDto;
import com.jifs.server.dto.IdDto;
import com.jifs.server.dto.group.CreateGroup;
import com.jifs.server.dto.group.UpdateGroup;
import com.jifs.server.dto.response.AccountResponseDto;
import com.jifs.server.dto.response.LoginResponseDto;
import com.jifs.server.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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
public class UserController {
    private AccountService accountService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, path = "/create")
    public ResponseEntity<String> createAccount(@RequestBody @Validated(CreateGroup.class) AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<LoginResponseDto> login(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.login(accountDto),HttpStatus.OK);
    }

    @GetMapping(path = "/id")
    public ResponseEntity<AccountResponseDto> getUser(@RequestBody IdDto id) {
        return new ResponseEntity<>(accountService.getAccount(id), HttpStatus.OK);
    }

    @PatchMapping(path = "/id")
    public ResponseEntity<String> updateUser(@RequestBody @Validated(UpdateGroup.class) AccountDto accountDto) {
        return new ResponseEntity<>(accountService.updateAccount(accountDto), HttpStatus.ACCEPTED);
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<List<AccountResponseDto>> allUsers() {
        return new ResponseEntity<>(accountService.allUsers(),HttpStatus.OK);
    }
}
