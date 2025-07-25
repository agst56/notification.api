package com.mcs.notification.api.controller;

import com.mcs.notification.api.dto.CreateMessageDTO;
import com.mcs.notification.api.dto.NotificationResponse;
import org.springframework.http.ResponseEntity;

public interface MessageController {
    ResponseEntity<Void> create(CreateMessageDTO request);

    ResponseEntity<NotificationResponse> getAll();
}
