package com.example.rpa.dev.rpadev.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Base64;
import java.util.Random;

@Service
public class Base64EncodingService {
    SseEmitter emitter = new SseEmitter();
    private boolean isCancelled = false;

    public SseEmitter encodeString(String input) {

        new Thread(() -> {
            try {
                String base64 = Base64.getEncoder().encodeToString(input.getBytes());
                for (char ch : base64.toCharArray()) {
                    if (isCancelled) break;
                    emitter.send(String.valueOf(ch));
                    Thread.sleep(new Random().nextInt(4000) + 1000);
                }
                emitter.complete();
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        }).start();
        return emitter;
    }

    public void cancelEncoding() {
        isCancelled = true;
        if (emitter != null) {
            emitter.complete();
        }
    }
}
