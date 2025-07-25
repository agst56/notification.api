package com.mcs.notification.api.controller;

import com.mcs.notification.api.dto.CreateMessageDTO;
import com.mcs.notification.api.dto.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MessageControllerDecorator implements MessageController{

    private final MessageController messageControllerImpl;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateMessageDTO request) {
        return messageControllerImpl.create(request);
    }

    @GetMapping
    public ResponseEntity<NotificationResponse> getAll() {
        return messageControllerImpl.getAll();
    }
}
