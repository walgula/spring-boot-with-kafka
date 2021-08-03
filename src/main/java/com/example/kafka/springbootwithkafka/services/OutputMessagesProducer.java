package com.example.kafka.springbootwithkafka.services;

import com.example.kafka.springbootwithkafka.models.OutputMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;



@Service
public class OutputMessagesProducer {
    private static final Logger logger = LoggerFactory.getLogger(OutputMessagesProducer.class);
    private static final String TOPIC = "output_topic";

    @Autowired
    private KafkaTemplate<String, OutputMessage> kafkaTemplate;

    public void sendMessage(OutputMessage outputMessage) {
        logger.info(String.format("#### -> Producing output message -> %s", outputMessage));
        this.kafkaTemplate.send(TOPIC, outputMessage);
    }

}
