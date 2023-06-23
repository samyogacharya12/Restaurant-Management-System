package com.example.resturantservice.service;

import com.example.resturantservice.dto.OrderRequestDTO;
import com.example.resturantservice.dto.OrderResponseDTO;

import java.util.Map;

public interface ResturantOrderService {

    Map<String, OrderResponseDTO> generateRandomOrders();

    OrderRequestDTO send(OrderRequestDTO orderRequestDTO);

    OrderResponseDTO getOrders(String orderId);

}
