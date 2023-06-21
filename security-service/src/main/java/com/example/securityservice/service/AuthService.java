package com.example.securityservice.service;

import com.example.securityservice.dto.UserDto;

public interface AuthService {

    UserDto save(UserDto userDto);

    UserDto findByUserName(String userName);

    String generateToken(String username);

    void validateToken(String token);
}
