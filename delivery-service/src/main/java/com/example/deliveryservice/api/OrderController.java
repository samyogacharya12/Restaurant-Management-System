package com.example.deliveryservice.api;

import com.example.deliveryservice.dto.OrderResponseDto;
import com.example.deliveryservice.dto.UserResponseDto;
import com.example.deliveryservice.exception.BadRequestAlertException;
import com.example.deliveryservice.service.RestaurantService;
import com.example.deliveryservice.service.UserService;
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

    @Autowired
    private UserService userService;


    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrders(@RequestHeader(HttpHeaders.AUTHORIZATION)
                                                      String token,
                                                      @RequestHeader("loggedInUser")
                                                      String userName,
                                                      @PathVariable(value = "orderId")
                                                      String orderId) {
        logger.info("fetching orders from resturant", token);
        UserResponseDto userResponseDto = this.userService.findByUserName(token, userName);
        if (userResponseDto.getRole().equals("STAFF")) {
            throw new BadRequestAlertException("Sorry you are not authorize for this request",
                HttpStatus.BAD_REQUEST);
        }
        OrderResponseDto orderResponseDto = this.restaurantService.findByOrderId(token, orderId);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }
}
