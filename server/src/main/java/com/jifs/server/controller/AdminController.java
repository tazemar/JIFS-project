package com.jifs.server.controller;

import com.jifs.server.dto.response.AccountResponseDto;
import com.jifs.server.dto.IdDto;
import com.jifs.server.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/admins")
@PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
@RestController
@AllArgsConstructor
public class AdminController {
    private AccountService accountService;

    @PatchMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<String> promoteAdministrator(@RequestBody IdDto id) {
        return new ResponseEntity<>(accountService.promoteAdministrator(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDto>> allUsers() {
        return new ResponseEntity<>(accountService.allAccounts(),HttpStatus.OK);
    }
}
