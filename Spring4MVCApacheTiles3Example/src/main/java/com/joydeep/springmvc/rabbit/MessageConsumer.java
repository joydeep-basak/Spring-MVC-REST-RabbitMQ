package com.joydeep.springmvc.rabbit;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.joydeep.springmvc.model.DataModel;

@Component
public class MessageConsumer {

    private static final Logger logger = Logger.getLogger(MessageConsumer.class);

    @RabbitListener(queues = {SpringAmqpConfig.queueName})
    public void receiveMessage(String message) {
        logger.info("Received Message: " + message);
    }
    
    @RabbitListener(queues = {SpringAmqpConfig.queueNameObject})
    public void receiveMessage(DataModel message) {
        logger.info("Received Message: " + message);
        logger.info("Data Model value :: " + message.getData());
    }
}