package com.mcs.notification.api.service.impl;

import com.mcs.notification.api.config.RabbitConfigApi;
import com.mcs.notification.api.dto.CreateMessageDTO;
import com.mcs.notification.api.dto.NotificationDTO;
import com.mcs.notification.api.dto.NotificationRequested;
import com.mcs.notification.api.dto.NotificationResponse;
import com.mcs.notification.api.dto.enums.NotificationStatus;
import com.mcs.notification.api.entity.Message;
import com.mcs.notification.api.repository.MessageRepository;
import com.mcs.notification.api.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepo;
    private final RabbitTemplate rabbitTemplate;

    public Long createAndPublish(CreateMessageDTO request) {
        Message msg = Message.builder()
                .category(request.category())
                .body(request.body())
                .createdAt(LocalDateTime.now())
                .status(NotificationStatus.CREATED.name())
                .build();
        msg = messageRepo.save(msg);

        NotificationRequested evt = new NotificationRequested(
                msg.getId(), msg.getCategory().name(), msg.getBody(), msg.getCreatedAt());

        rabbitTemplate.convertAndSend(RabbitConfigApi.EVENTS_EXCHANGE, "", evt);

        return msg.getId();
    }

    //TODO: Listen to event when notification completed

    public NotificationResponse getNotifications(String sortBy, String sortOrder){
        Sort.Direction sortDirection = Sort.Direction.fromString(sortOrder);
        List<Message> messages =  messageRepo.findAll(Sort.by(sortDirection, sortBy));
        List<NotificationDTO> dtoList = messages.stream()
                .map(NotificationDTO::fromEntity)
                .toList();
        return new NotificationResponse(dtoList);
    }
}
