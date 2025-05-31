package com.example.deliveryservice.api;

import com.example.deliveryservice.dto.OrderResponseDto;
import com.example.deliveryservice.dto.UserResponseDto;
import com.example.deliveryservice.exception.UnAuthorizeRequestAlertException;
import com.example.deliveryservice.service.RestaurantService;
import com.example.deliveryservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/delivery")
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);



    @Value("${security.service.url}")
    private String url;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;


    @GetMapping("/orders/{orderId}")
//    @Retry(name="getOrders",fallbackMethod="fallbackAfterRetry")
    @CircuitBreaker(name = "CircuitBreakerService",fallbackMethod = "fallbackAfterRetry")
    public ResponseEntity<OrderResponseDto> getOrders(@RequestHeader(HttpHeaders.AUTHORIZATION)
                                                      String token,
                                                      @RequestHeader("loggedInUser")
                                                      String userName,
                                                      @PathVariable(value = "orderId")
                                                      String orderId) {
        logger.info("fetching orders from resturant", token);
        UserResponseDto userResponseDto = this.userService.findByUserName(token, userName);
        if (userResponseDto.getRole().equals("STAFF")) {
            throw new UnAuthorizeRequestAlertException("Sorry you are not authorize for this request",
                HttpStatus.UNAUTHORIZED);
        }
        OrderResponseDto orderResponseDto = this.restaurantService.findByOrderId(token, orderId);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }


    public ResponseEntity<OrderResponseDto>  fallbackAfterRetry(Exception e){
        logger.info(e.getMessage());
        return new ResponseEntity<>(new OrderResponseDto(),HttpStatus.OK);

    }

    @GetMapping("/orders/email")
    @CircuitBreaker(name = "CircuitBreaker",fallbackMethod = "fallbackAfterError")
    public ResponseEntity<String>  fetechUser(@RequestParam String email
        ,@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        UserResponseDto userResponseDto = this.userService.findByUserName(token, email);
        if(Objects.nonNull(userResponseDto.getRole())) {
            return new ResponseEntity<>("user is present", HttpStatus.OK);
        }
        return new ResponseEntity<>("user is not present", HttpStatus.OK);
    }

    public ResponseEntity<String>  fallbackAfterError(Exception e){
        logger.info(e.getMessage());
        return new ResponseEntity<>("fall back response",HttpStatus.OK);

    }

}
