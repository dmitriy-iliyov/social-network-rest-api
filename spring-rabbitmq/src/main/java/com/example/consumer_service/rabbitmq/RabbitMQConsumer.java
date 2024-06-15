package com.example.consumer_service.rabbitmq;

import com.example.consumer_service.models.DTO.user.UserRegistrationDTO;
import com.example.consumer_service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@EnableRabbit
@Component
@RequiredArgsConstructor
public class RabbitMQConsumer {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = "userQueue")
    public void processUserQueue(UserRegistrationDTO userRegistrationDTO){
        userService.save(userRegistrationDTO);
        logger.info("User successfully created.");
    }

    @RabbitListener(queues = "postQueue")
    public void processPostQueue(String message){
    }

    @RabbitListener(queues = "categoryQueue")
    public void processCategoryQueue(String message){
    }
}
