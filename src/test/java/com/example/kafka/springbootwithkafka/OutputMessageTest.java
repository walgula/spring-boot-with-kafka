package com.example.kafka.springbootwithkafka;

import com.example.kafka.springbootwithkafka.models.OutputMessage;
import com.example.kafka.springbootwithkafka.services.OutputMessagesConsumer;
import com.example.kafka.springbootwithkafka.services.OutputMessagesProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@EmbeddedKafka
@SpringBootTest(properties = "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OutputMessageTest {

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private OutputMessagesProducer producer;

    @Autowired
    private OutputMessagesConsumer consumer;

    @Autowired
    private ObjectMapper objectMapper;

    private final static String TOPIC_NAME = "output_topic";


    private KafkaMessageListenerContainer<String, String> container;
    private BlockingQueue<ConsumerRecord<String, String>> records;


    @BeforeAll
    void setUp() {
        DefaultKafkaConsumerFactory<String, String> consumerFactory = new DefaultKafkaConsumerFactory<>(getConsumerProperties());
        ContainerProperties containerProperties = new ContainerProperties(TOPIC_NAME);
        container = new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);
        records = new LinkedBlockingQueue<>();
        container.setupMessageListener((MessageListener<String, String>) records::add);
        container.start();
    }

    private Map<String, Object> getConsumerProperties() {
        return Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, embeddedKafkaBroker.getBrokersAsString(),
                ConsumerConfig.GROUP_ID_CONFIG, "output",
                ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true",
                ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "10",
                ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "60000",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    }

    @AfterAll
    void tearDown() {
        container.stop();
    }

    @Test
    void testShouldReturnValidConsumedOutputMessageProducedByOutputMessagesProducer() throws InterruptedException, IOException {
        OutputMessage outputMessage = new OutputMessage();
        outputMessage.setCountry("GERMANY");
        outputMessage.setCity("BERLIN");
        outputMessage.setPopulation(3645000);

        producer.sendMessage(outputMessage);

        ConsumerRecord<String, String> message = records.poll(5, TimeUnit.SECONDS);

        assertNotNull(message);

        OutputMessage result =  objectMapper.readValue(message.value(), OutputMessage.class);
        assertNotNull(result);
        assertEquals("GERMANY", result.getCountry());
        assertEquals("BERLIN", result.getCity());
        assertEquals(3645000, result.getPopulation());
    }
}