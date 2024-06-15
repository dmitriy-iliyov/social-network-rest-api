package com.example.socialnetworkrestapi.rabbitmq;

import com.example.socialnetworkrestapi.models.DTO.user.UserRegistrationDTO;
import com.example.socialnetworkrestapi.services.UserService;
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
        logger.info("User successfully created.");
    }

    @RabbitListener(queues = "postQueue")
    public void processPostQueue(String message){
    }

    @RabbitListener(queues = "categoryQueue")
    public void processCategoryQueue(String message){
    }
}
