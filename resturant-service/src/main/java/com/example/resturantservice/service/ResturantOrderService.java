package com.example.resturantservice.service;

import com.example.resturantservice.dto.OrderResponseDTO;

import java.util.Map;

public interface ResturantOrderService {

    Map<String, OrderResponseDTO> generateRandomOrders();

    OrderResponseDTO getOrders(String orderId);
}
