package com.mcs.notification.api.dto;

import java.util.List;

public record NotificationResponse(
        List<NotificationDTO> data
) {
}
