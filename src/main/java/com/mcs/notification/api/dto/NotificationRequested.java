package com.mcs.notification.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;


public record NotificationRequested(
        Long messageId,
        String category,
        String body,
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        LocalDateTime createdAt
) {}
