package com.jifs.server.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
public class TestController {

    @GetMapping()
    public ResponseEntity<String> createAccount() {
        return new ResponseEntity<>("test", HttpStatus.OK);
    }
}
