package com.example.kafka.springbootwithkafka.controllers;
import com.example.kafka.springbootwithkafka.models.InputMessage;
import com.example.kafka.springbootwithkafka.services.InputMessagesProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//Handy REST controller for application testing

@RestController
public class PublishInputMessageCtrl {
    private final InputMessagesProducer producer;
    @Autowired
    public PublishInputMessageCtrl(InputMessagesProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/cities")
    public void publishCity(@RequestBody InputMessage inputMessage) {
        this.producer.sendMessage(inputMessage);
    }

}
