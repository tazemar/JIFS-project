package com.jifs.server.service.impl;

import com.jifs.server.service.JWTService;
import org.springframework.stereotype.Service;

@Service
public class JWTServiceImpl implements JWTService {
    @Override
    public String generateToken() {
        return "faketokentemp";
    }
}
