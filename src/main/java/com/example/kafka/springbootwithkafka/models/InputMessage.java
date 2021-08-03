package com.example.kafka.springbootwithkafka.models;

public class InputMessage {
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "InputMessage{" +
                "city='" + city + '\'' +
                '}';
    }
}
