package com.example.gateway_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Con {
     
    
    @GetMapping("/start")
    public void start() {
           System.out.println("---------------------------------");
    }
}
