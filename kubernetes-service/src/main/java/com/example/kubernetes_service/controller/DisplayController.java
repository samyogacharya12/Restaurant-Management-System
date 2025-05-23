package com.example.kubernetes_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DisplayController {

    @GetMapping("/fetch")
    public String fetchData(){
        return "data";
    }
}
