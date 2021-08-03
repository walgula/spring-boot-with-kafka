package com.example.kafka.springbootwithkafka.models;

import javax.persistence.*;

@Entity
@Table(name="countries")
public class Country {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="city", length=100, nullable=false, unique=false)
    private String city;

    @Column(name="country", length=100, nullable=false, unique=false)
    private String country;

    public Country() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
