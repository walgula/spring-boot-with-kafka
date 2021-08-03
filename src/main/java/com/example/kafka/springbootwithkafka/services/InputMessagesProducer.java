package com.example.kafka.springbootwithkafka.services;

import com.example.kafka.springbootwithkafka.models.InputMessage;
import com.example.kafka.springbootwithkafka.models.OutputMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class InputMessagesProducer {
    private static final Logger logger = LoggerFactory.getLogger(InputMessagesProducer.class);
    private static final String TOPIC = "input_topic";

    @Autowired
    private KafkaTemplate<String, InputMessage> kafkaTemplate;

    public void sendMessage(InputMessage inputMessage) {
        logger.info(String.format("#### -> Producing input message -> %s", inputMessage));
        this.kafkaTemplate.send(TOPIC, inputMessage);
    }

}
