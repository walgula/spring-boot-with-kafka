package com.example.kafka.springbootwithkafka.services;

import com.example.kafka.springbootwithkafka.models.InputMessage;
import com.example.kafka.springbootwithkafka.models.OutputMessage;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class InputMessagesConsumer {
    private final Logger logger = LoggerFactory.getLogger(InputMessagesConsumer.class);
    private final MessageComposeService messageComposeService;
    private final OutputMessagesProducer outputMessagesProducer;

    @Autowired
    public InputMessagesConsumer(MessageComposeService messageComposeService, OutputMessagesProducer outputMessagesProducer) {
        this.messageComposeService = messageComposeService;
        this.outputMessagesProducer = outputMessagesProducer;
    }

    @KafkaListener(topics = "input_topic", groupId = "group_id")
    public void consume(InputMessage inputMessage) throws IOException, ResourceNotFoundException {
        if (inputMessage != null){
            logger.info(String.format("#### -> Consumed input message -> %s", inputMessage));
            OutputMessage outputMessage = messageComposeService.compose(inputMessage.getCity());
            this.outputMessagesProducer.sendMessage(outputMessage);
        }else{
            logger.error(String.format("#### -> Input message is null"));
        }

    }
}
