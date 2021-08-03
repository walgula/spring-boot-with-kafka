package com.example.kafka.springbootwithkafka.repositories;

import com.example.kafka.springbootwithkafka.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByCity(String city);
}
