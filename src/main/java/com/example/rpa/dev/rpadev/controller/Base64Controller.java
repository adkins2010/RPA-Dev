package com.example.rpa.dev.rpadev.controller;

import com.example.rpa.dev.rpadev.service.Base64EncodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class Base64Controller {

    @Autowired
    private Base64EncodingService base64EncodingService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/encode")
    public SseEmitter encode(@RequestParam String input) {
        return base64EncodingService.encodeString(input);
    }

    @PostMapping("/cancel")
    public void cancelEncoding() {
        base64EncodingService.cancelEncoding();
    }
}
