package com.example.springpostgresqlrabbitmq.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@EnableRabbit
@Component
public class RabbitMQListener {

    private final Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);

    @RabbitListener(queues = "userQueue")
    public void processUserQueue(String message){
        logger.info("From user queue: " + message);
    }

    @RabbitListener(queues = "postQueue")
    public void processPostQueue(String message){
        logger.info("From post queue:  " + message);
    }

    @RabbitListener(queues = "categoryQueue")
    public void processCategoryQueue(String message){
        logger.info("From category queue:  " + message);
    }
}
