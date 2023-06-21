package com.example.deliveryservice.api;

import com.example.deliveryservice.dto.OrderResponseDto;
import com.example.deliveryservice.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);


    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrders(@RequestHeader(HttpHeaders.AUTHORIZATION)
                                                      String token,
                                                      @PathVariable(value = "orderId")
                                                      String orderId) {
        logger.info("fetching orders from resturant",token);
        OrderResponseDto orderResponseDto = this.restaurantService.findByOrderId(token,orderId);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }
}
