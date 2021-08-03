package com.example.kafka.springbootwithkafka.services;

import com.example.kafka.springbootwithkafka.models.CityPopulationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class CityPopulationService extends RestTemplate{
    final String uri = "http://localhost:8080/cities";
    final ObjectMapper objectMapper = new ObjectMapper();

    public List<CityPopulationDto> get() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String json =  restTemplate.getForObject(uri, String.class);
        return objectMapper.readValue(json, new TypeReference<List<CityPopulationDto>>(){});
    }
}
