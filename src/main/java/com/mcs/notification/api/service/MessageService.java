package com.mcs.notification.api.service;

import com.mcs.notification.api.dto.CreateMessageDTO;
import com.mcs.notification.api.dto.NotificationResponse;


public interface MessageService {
     Long createAndPublish(CreateMessageDTO request);
     NotificationResponse getNotifications(String sortBy, String sortOrder);
}
