package com.example.kafka.springbootwithkafka.controllers;

import com.example.kafka.springbootwithkafka.models.CityPopulationDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CityPopulationCtrl {
    @GetMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CityPopulationDto> getCities(){
        List<CityPopulationDto> cities = new ArrayList<>();

        CityPopulationDto berlin = new CityPopulationDto();
        berlin.setCity("BERLIN");
        berlin.setPopulation(3645000);
        cities.add(berlin);

        CityPopulationDto warsaw = new CityPopulationDto();
        warsaw.setCity("WARSAW");
        warsaw.setPopulation(1765000);
        cities.add(warsaw);

        return cities;
    }
}
