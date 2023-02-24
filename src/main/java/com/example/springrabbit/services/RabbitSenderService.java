package com.example.springrabbit.services;

import com.example.springrabbit.dto.request.OrderDtoRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class RabbitSenderService {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitSenderService(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingKey;

    public RabbitSenderService() {
    }

    public String send(OrderDtoRequest orderDtoRequest) throws JsonProcessingException {

        OrderDtoRequest result = (OrderDtoRequest) rabbitTemplate.convertSendAndReceive(
                exchange,
                routingKey,
                orderDtoRequest);
        assert result != null;
        return result.toString();
    }
}
