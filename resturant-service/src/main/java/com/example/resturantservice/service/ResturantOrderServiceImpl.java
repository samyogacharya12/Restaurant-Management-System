package com.example.resturantservice.service;

import com.example.resturantservice.dto.OrderRequestDTO;
import com.example.resturantservice.dto.OrderResponseDTO;
import com.example.resturantservice.dto.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ResturantOrderServiceImpl implements ResturantOrderService {

    private static final Logger logger= LoggerFactory.getLogger(ResturantOrderServiceImpl.class);


    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public OrderResponseDTO getOrders(String orderId) {
        return generateRandomOrders().get(orderId);
    }

    @Override
    public Map<String, OrderResponseDTO> generateRandomOrders() {
        Map<String, OrderResponseDTO> orderMap = new HashMap<>();
        orderMap.put("35fds631", new OrderResponseDTO("35fds63", "VEG-MEALS", 1, 199, new Date().toString(), OrderStatus.PENDING, 15));
        orderMap.put("9u71245h", new OrderResponseDTO("9u71245h", "HYDERABADI DUM BIRYANI", 2, 641, new Date().toString(), OrderStatus.PROCESSED, 59));
        orderMap.put("37jbd832", new OrderResponseDTO("37jbd832", "PANEER BUTTER MASALA", 1, 325, new Date().toString(), OrderStatus.DELIVERED, 0));
        return orderMap;
    }

    @Override
    public OrderRequestDTO send(OrderRequestDTO orderRequestDTO) {
        logger.info("sending kafka message to order");
        try {
            orderRequestDTO.setOrderDate(LocalDateTime.now().toString());
            orderRequestDTO.setStatus(OrderStatus.PENDING.toString());
            this.kafkaTemplate.send("restaurant_order_channel", orderRequestDTO);
            return orderRequestDTO;
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }
}
