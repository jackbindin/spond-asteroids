package com.example.demo.nasaApi;

import com.example.demo.model.Asteroid;
import com.example.demo.model.AsteroidBrowse;
import com.example.demo.model.AsteroidFeed;
import reactor.core.publisher.Mono;

public interface NasaAsteroidApiInterface {
    Mono<Asteroid> asteroidById(String id);
    Mono<AsteroidFeed> getAsteroidsBetweenDate(String start, String end);
    Mono<AsteroidBrowse> getAllAsteroids();
}
