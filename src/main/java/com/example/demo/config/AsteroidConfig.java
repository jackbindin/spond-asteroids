package com.example.demo.config;

import com.example.demo.nasaApi.NasaAsteroidApi;
import com.example.demo.nasaApi.NasaAsteroidApiInterface;
import com.example.demo.service.AsteroidService;
import com.example.demo.service.AsteroidServiceInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AsteroidConfig {
    @Bean
    public NasaAsteroidApiInterface nasaApiRepo(){
        return new NasaAsteroidApi();
    }

    @Bean
    public AsteroidServiceInterface asteroidService(){
        return new AsteroidService();
    }

    @Bean
    public WebClient webClient() {
        final int size = 16 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();
        return WebClient.builder()
                .baseUrl("https://api.nasa.gov/neo/rest/v1")
                .exchangeStrategies(strategies)
                .build();
    }


}
