package com.example.kafka.springbootwithkafka.models;

public class CityPopulationDto {
    private Integer population;
    private String city;

    public CityPopulationDto() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
