package com.joydeep.springmvc.rabbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.joydeep.springmvc.model.DataModel;

@RestController
@RequestMapping("/rest")
public class MessageController {

	@Autowired
    private MessageProducer messageProducer;

    @Autowired
    public MessageController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @RequestMapping(value="/messages", method= RequestMethod.POST)
    @ResponseStatus(value= HttpStatus.CREATED)
    public void sendMessage(@RequestBody String message) {
        messageProducer.sendMessage(message);
    }
    
    @RequestMapping(value="/usermessage", method= RequestMethod.POST)
    @ResponseStatus(value= HttpStatus.CREATED)
    public void usermessage(@RequestBody String message) {
    	DataModel model = new DataModel();
    	model.setData(message);
        messageProducer.sendObjectMessage(model);
    }
}
