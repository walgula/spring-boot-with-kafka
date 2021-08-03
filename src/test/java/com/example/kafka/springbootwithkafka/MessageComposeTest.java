package com.example.kafka.springbootwithkafka;

import com.example.kafka.springbootwithkafka.models.OutputMessage;
import com.example.kafka.springbootwithkafka.services.MessageComposeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MessageComposeTest {
    @Autowired
    private MessageComposeService service;

    @Test
    void testIfServiceReturnsRightOutputMessageByGivenCity() throws JsonProcessingException {
        OutputMessage berlin_message = service.compose("BERLIN");
        assertEquals("BERLIN", berlin_message.getCity());
        assertEquals("GERMANY", berlin_message.getCountry());
        assertEquals(3645000, berlin_message.getPopulation());

        OutputMessage warsaw_message = service.compose("WARSAW");
        assertEquals("WARSAW", warsaw_message.getCity());
        assertEquals("POLAND", warsaw_message.getCountry());
        assertEquals(1765000, warsaw_message.getPopulation());
    }

    @Test
    void testIfServiceThrowsExceptionWhenCityDoesNotExistInDatabase() {
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.compose("BE"));

        String expectedMessage = "No data for given city: BE";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
