package com.mcs.notification.api.service;

import com.mcs.notification.api.config.RabbitConfigApi;
import com.mcs.notification.api.dto.CreateMessageDTO;
import com.mcs.notification.api.dto.NotificationRequested;
import com.mcs.notification.api.entity.Message;
import com.mcs.notification.api.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepo;
    private final RabbitTemplate rabbitTemplate;

    public Long createAndPublish(CreateMessageDTO request) {
        Message msg = Message.builder()
                .category(request.category())
                .body(request.body())
                .createdAt(LocalDateTime.now())
                .build();
        msg = messageRepo.save(msg);

        NotificationRequested evt = new NotificationRequested(
                msg.getId(), msg.getCategory().name(), msg.getBody(), msg.getCreatedAt());

        rabbitTemplate.convertAndSend(RabbitConfigApi.EVENTS_EXCHANGE, "", evt);
        return msg.getId();
    }
}
