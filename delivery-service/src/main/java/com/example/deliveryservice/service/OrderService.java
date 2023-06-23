package com.example.deliveryservice.service;

import com.example.deliveryservice.dto.OrderResponseDto;
import com.example.deliveryservice.entity.Order;
import com.example.deliveryservice.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @KafkaListener(topics = "restaurant_order_channel", groupId = "restaurant_order_channel_group")
    public void save(String data) {
        try {
            logger.info(data);
            ObjectMapper objectMapper = new ObjectMapper();
            Order order = objectMapper.readValue(data, Order.class);
            this.orderRepository.save(order);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
