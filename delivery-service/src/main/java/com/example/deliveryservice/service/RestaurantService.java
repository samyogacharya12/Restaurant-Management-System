package com.example.deliveryservice.service;

import com.example.deliveryservice.dto.OrderResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "restaurant-app",url = "localhost:8080/restaurant")
public interface RestaurantService {

    @GetMapping("/orders/{orderId}")
    OrderResponseDto findByOrderId(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                         @PathVariable(value = "orderId") String orderId);
}
