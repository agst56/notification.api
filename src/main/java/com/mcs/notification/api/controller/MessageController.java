package com.mcs.notification.api.controller;

import com.mcs.notification.api.dto.CreateMessageDTO;
import com.mcs.notification.api.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateMessageDTO request) {
        messageService.createAndPublish(request);
        return ResponseEntity.accepted().build();
    }
}
