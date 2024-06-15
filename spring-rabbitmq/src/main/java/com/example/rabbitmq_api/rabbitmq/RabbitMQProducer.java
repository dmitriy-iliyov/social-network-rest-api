package com.example.rabbitmq_api.rabbitmq;


import com.example.rabbitmq_api.models.DTO.user.UserRegistrationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessageToUserQueue(UserRegistrationDTO userRegistrationDTO){
        rabbitTemplate.convertAndSend("userQueue", userRegistrationDTO);
    }
}
