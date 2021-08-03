package com.example.kafka.springbootwithkafka.services;


import com.example.kafka.springbootwithkafka.models.OutputMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OutputMessagesConsumer {
    private final Logger logger = LoggerFactory.getLogger(OutputMessagesConsumer.class);




    @KafkaListener(topics = "output_topic", groupId = "group_id")
    public void consume(OutputMessage outputMessage) throws IOException {
        if (outputMessage != null){
            logger.info(String.format("#### -> Consumed output message -> %s", outputMessage));

        }else{
            logger.error(String.format("#### -> Output message is null"));
        }

    }
}
