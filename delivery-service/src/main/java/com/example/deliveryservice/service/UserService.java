package com.example.deliveryservice.service;

import com.example.deliveryservice.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(name="SECURITY-SERVICE")
public interface UserService {

    @GetMapping("/auth/user")
    UserResponseDto findByUserName(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                   @RequestParam(value = "userName") String userName);


    @GetMapping("/auth/email")
    UserResponseDto findByEmail(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                   @RequestParam(value = "email") String userName);
}
