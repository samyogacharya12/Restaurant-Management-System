package com.example.resturantservice.api;

import com.example.resturantservice.dto.OrderResponseDTO;
import com.example.resturantservice.service.ResturantOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private ResturantOrderService resturantOrderService;

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable String orderId) {
        OrderResponseDTO orderResponseDTO=resturantOrderService.getOrders(orderId);
        return new ResponseEntity<>(orderResponseDTO, HttpStatus.OK);
    }
}
