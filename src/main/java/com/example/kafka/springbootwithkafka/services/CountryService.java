package com.example.kafka.springbootwithkafka.services;

import com.example.kafka.springbootwithkafka.models.Country;
import com.example.kafka.springbootwithkafka.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country getCountry(String city){
        return this.countryRepository.findByCity(city);
    }
}
