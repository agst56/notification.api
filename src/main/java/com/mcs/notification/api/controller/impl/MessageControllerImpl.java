package com.mcs.notification.api.controller.impl;

import com.mcs.notification.api.controller.MessageController;
import com.mcs.notification.api.dto.CreateMessageDTO;
import com.mcs.notification.api.dto.NotificationResponse;
import com.mcs.notification.api.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageControllerImpl implements MessageController {

    private final MessageService messageService;

    public ResponseEntity<Void> create(CreateMessageDTO request) {
        try {
            messageService.createAndPublish(request);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<NotificationResponse> getAll() {
        try {
            // TODO: sortBy and sortOrder should be params
            return ResponseEntity.ok(messageService.getNotifications("id", "DESC"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
