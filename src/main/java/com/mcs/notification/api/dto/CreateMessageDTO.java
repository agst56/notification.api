package com.mcs.notification.api.dto;


import com.mcs.notification.api.dto.enums.NotificationCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateMessageDTO(
        @NotNull(message = "Category is required")
        NotificationCategory category,

        @NotBlank(message = "Message body is required")
        String body
) {}
