package com.mcs.notification.api.dto;

import com.mcs.notification.api.dto.enums.NotificationCategory;
import com.mcs.notification.api.entity.Message;

import java.time.LocalDateTime;

public record NotificationDTO(
        Long id,
        NotificationCategory category,
        String body,
        String status,
        LocalDateTime createdAt
) {
    public static NotificationDTO fromEntity(Message message) {
        return new NotificationDTO(
                message.getId(),
                message.getCategory(),
                message.getBody(),
                message.getStatus(),
                message.getCreatedAt()
        );
    }
}
