package com.example.kafka.springbootwithkafka.models;

public class OutputMessage {
    private String city;
    private String country;
    private Integer population;



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }


    @Override
    public String toString() {
        return "OutputMessage{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", population=" + population +
                '}';
    }
}
