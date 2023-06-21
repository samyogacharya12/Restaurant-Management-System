package com.example.securityservice.service;

import java.security.Key;
import java.util.Map;

public interface JwtService {

    String generateToken(String userName);

    void validateToken(final String token);

    Key getSignKey();


}
