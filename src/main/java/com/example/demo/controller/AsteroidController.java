package com.example.demo.controller;

import com.example.demo.model.Asteroid;
import com.example.demo.model.AsteroidDistance;
import com.example.demo.service.AsteroidServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class AsteroidController {
    @Autowired
    AsteroidServiceInterface asteroidService;

    @GetMapping("asteroids/{id}")
    public ResponseEntity<Asteroid> getAsteroidName(@PathVariable String id){
        return new ResponseEntity<>(asteroidService.getAsteroidById(id), HttpStatus.OK);
    }

    @GetMapping("asteroids/closest/{start}&{end}")
    public ResponseEntity<List<AsteroidDistance>> getClosestAsteroidsByDate(@PathVariable String start, @PathVariable String end){
        return new ResponseEntity<>(asteroidService.getClosestAsteroidsByDate(start,end), HttpStatus.OK);
    }

    @GetMapping("asteroids/biggest/{year}")
    public ResponseEntity<Asteroid> getLargestAsteroidByYear(@PathVariable String year){
        return new ResponseEntity<>(asteroidService.getLargestAsteroidByYear(year), HttpStatus.OK);
    }
}
