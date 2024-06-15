package com.example.socialnetworkrestapi.rabbitmq;


import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessageToPhase_1_Queue(){
        rabbitTemplate.convertAndSend("phase1Queue");
    }
}
