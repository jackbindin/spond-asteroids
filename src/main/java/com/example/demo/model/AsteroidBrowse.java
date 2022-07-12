package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AsteroidBrowse {
    @JsonProperty("near_earth_objects")
    private List<Asteroid> asteroids;
}
