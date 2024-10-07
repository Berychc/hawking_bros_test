package com.example.hawking_bros.controller;

import com.example.hawking_bros.entity.MsgA;
import com.example.hawking_bros.entity.MsgB;
import com.example.hawking_bros.enums.Lng;
import com.example.hawking_bros.service.AdapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class AdapterController {

    private final AdapterService service;

    @PostMapping
    public ResponseEntity<String> receiveMessage(@RequestBody MsgA message) {
        if (message.getMsg() == null || message.getMsg().isEmpty()) {
            return ResponseEntity.badRequest().body("Пустое сообщение");
        }

        try {
            if (Lng.RU.equals(message.getLng())) {
                MsgB enrichedMessage = service.enrichMessage(message);
                return ResponseEntity.ok("Сообщение успешно обработано: " + enrichedMessage);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Сообщение не RU");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка обработки: " + e.getMessage());
        }
    }
}
