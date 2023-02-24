package com.example.springrabbit.listeners;

import com.example.springrabbit.dto.request.OrderDtoRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;

@Slf4j
@AllArgsConstructor
public class OrderListener {

    @SendTo(value = "${spring.rabbitmq.reply-queue}")
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public OrderDtoRequest listen(@Payload OrderDtoRequest orderDtoRequest, Message message) {
        orderDtoRequest.setName("Hello!");
        return orderDtoRequest;
    }
}
