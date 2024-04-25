package com.example.rpa.dev.rpadev.controller;

import com.example.rpa.dev.rpadev.entity.CustomerEntity;
import com.example.rpa.dev.rpadev.service.Base64EncodingService;
import com.example.rpa.dev.rpadev.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/")
    public List<CustomerEntity> getAll(@RequestParam String input) {
        return service.getAllCustomers();
    }

    @PostMapping("/cancel")
    public void cancelEncoding() {
        base64EncodingService.cancelEncoding();
    }
}
