package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class AsteroidFeed {
    @JsonProperty("element_count")
    private int elementCount;
    @JsonProperty("near_earth_objects")
    private Map<String, List<Asteroid>> asteroids;
}
