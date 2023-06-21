package com.example.resturantservice.service;

import com.example.resturantservice.dto.OrderResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ResturantOrderServiceImpl implements ResturantOrderService {


    @Override
    public OrderResponseDTO getOrders(String orderId) {
        return generateRandomOrders().get(orderId);
    }

    @Override
    public Map<String, OrderResponseDTO> generateRandomOrders() {
        Map<String, OrderResponseDTO> orderMap = new HashMap<>();
        orderMap.put("35fds631", new OrderResponseDTO("35fds63", "VEG-MEALS", 1, 199, new Date().toString(), "READY", 15));
        orderMap.put("9u71245h", new OrderResponseDTO("9u71245h", "HYDERABADI DUM BIRYANI", 2, 641, new Date().toString(), "PREPARING", 59));
        orderMap.put("37jbd832", new OrderResponseDTO("37jbd832", "PANEER BUTTER MASALA", 1, 325, new Date().toString(), "DELIVERED", 0));
        return orderMap;
    }
}
