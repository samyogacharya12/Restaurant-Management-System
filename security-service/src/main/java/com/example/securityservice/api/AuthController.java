package com.example.securityservice.api;

import com.example.securityservice.dto.UserDto;
import com.example.securityservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserDto> addNewUser(@RequestBody UserDto userDto) {
        UserDto userDto1 = service.save(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.OK);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody UserDto authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
            (authRequest.getName(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getName());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }

    @GetMapping("/user")
    public ResponseEntity<UserDto> findUserByUserName(@RequestParam(value = "userName") String userName){
        UserDto userDto=this.service.findByUserName(userName);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

}
