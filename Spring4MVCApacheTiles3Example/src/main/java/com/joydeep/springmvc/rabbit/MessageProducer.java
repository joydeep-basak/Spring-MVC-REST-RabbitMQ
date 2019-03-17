package com.joydeep.springmvc.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joydeep.springmvc.model.DataModel;

@Component
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(SpringAmqpConfig.queueName, message);
    }
    
    public void sendObjectMessage(DataModel model) {
        rabbitTemplate.convertAndSend(SpringAmqpConfig.queueNameObject, model);
    }
}
