package com.example.kafka.springbootwithkafka;

import com.example.kafka.springbootwithkafka.models.Country;
import com.example.kafka.springbootwithkafka.repositories.CountryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    private CountryRepository repository;

    @Test
    void testIfRepositoryReturnsRightCountryByGivenCity() {
        Country germany = repository.findByCity("BERLIN");
        assertEquals("GERMANY", germany.getCountry());
        Country poland = repository.findByCity("WARSAW");
        assertEquals("POLAND", poland.getCountry());
    }
    @Test
    void testIfRepositoryReturnsNullWhenCityDoesNotExistInDatabase() {
        Country country = repository.findByCity("UNKNOWNCITY");
        assertNull(country);
    }
}
