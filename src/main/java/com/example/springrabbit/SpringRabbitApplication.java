package com.example.springrabbit;

import com.example.springrabbit.dto.request.OrderDtoRequest;
import com.example.springrabbit.services.RabbitSenderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
@SpringBootApplication
public class SpringRabbitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRabbitApplication.class, args);
    }

    public SpringRabbitApplication(RabbitSenderService rabbitSenderService) {
        this.rabbitSenderService = rabbitSenderService;
    }

    private RabbitSenderService rabbitSenderService;

    @GetMapping("/api/order")
    public String createOrder() throws JsonProcessingException {
        return rabbitSenderService.send(new OrderDtoRequest("name", 123));
    }
}
