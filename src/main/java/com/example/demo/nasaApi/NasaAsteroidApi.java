package com.example.demo.nasaApi;

import com.example.demo.model.Asteroid;
import com.example.demo.model.AsteroidBrowse;
import com.example.demo.model.AsteroidFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class NasaAsteroidApi implements NasaAsteroidApiInterface{
    @Autowired
    WebClient webClient;
    private final String apiKey = "SVqZ8mPs91nvqPwgfxcuaQVTSSkGkadXwgEjrBO0";
    @Cacheable("asteroidById")
    public Mono<Asteroid> asteroidById(String id) {
        return this.webClient.get().uri("/neo/{id}?api_key="+apiKey, id)
                .retrieve().bodyToMono(Asteroid.class);
    }
    @Cacheable("asteroidBetweenDate")
    public Mono<AsteroidFeed> getAsteroidsBetweenDate(String start, String end){
        return this.webClient.get().uri("/feed?start_date={start}&end_date={end}&api_key="+apiKey,start,end)
                .retrieve().bodyToMono(AsteroidFeed.class);

    }

    @Cacheable("allAsteroids")
    public Mono<AsteroidBrowse> getAllAsteroids(){
        return this.webClient.get().uri("/neo/browse?api_key="+apiKey)
                .retrieve().bodyToMono(AsteroidBrowse.class);

    }


}
