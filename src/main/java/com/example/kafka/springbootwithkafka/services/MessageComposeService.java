package com.example.kafka.springbootwithkafka.services;

import com.example.kafka.springbootwithkafka.models.CityPopulationDto;
import com.example.kafka.springbootwithkafka.models.Country;
import com.example.kafka.springbootwithkafka.models.OutputMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageComposeService {
    private final CityPopulationService cityPopulationService;
    private final CountryService countryService;

    public MessageComposeService(CityPopulationService cityPopulationService, CountryService countryService) {
        this.cityPopulationService = cityPopulationService;
        this.countryService = countryService;
    }

    public OutputMessage compose(String city) throws JsonProcessingException, ResourceNotFoundException {
        List<CityPopulationDto> cityPopulationDtos = cityPopulationService.get();
        CityPopulationDto cityPopulationDto = cityPopulationDtos.stream()
                .filter(processedCityPopulationDto -> city.equals(processedCityPopulationDto.getCity()))
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException("No data for given city: "+city));
        Country country = countryService.getCountry(city);
        OutputMessage message = new OutputMessage();
        message.setCity(city);
        message.setCountry(country.getCountry());
        message.setPopulation(cityPopulationDto.getPopulation());
        return message;
    }
}
