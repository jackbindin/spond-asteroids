package com.example.demo.service;

import com.example.demo.model.Asteroid;
import com.example.demo.model.AsteroidBrowse;
import com.example.demo.model.AsteroidDistance;
import com.example.demo.model.AsteroidFeed;
import com.example.demo.nasaApi.NasaAsteroidApiInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AsteroidService implements AsteroidServiceInterface{
    @Autowired
    NasaAsteroidApiInterface nasaAsteroidApi;

    public Asteroid getAsteroidById(String id){
        return nasaAsteroidApi.asteroidById(id).block();
    }

    public  List<AsteroidDistance> getClosestAsteroidsByDate(String start, String end){
        Optional<AsteroidFeed> asteroidFeed = nasaAsteroidApi.getAsteroidsBetweenDate(start,end).blockOptional();
        return asteroidFeed.map(feed -> getClosestAsteroidsFromList(feed.getAsteroids())).orElseGet(LinkedList::new);
    }

    public Asteroid getLargestAsteroidByYear(String year){
        AsteroidBrowse asteroidBrowse = nasaAsteroidApi.getAllAsteroids().block();
        LinkedList<Asteroid> asteroidList = new LinkedList<>();
        for(Asteroid asteroid:asteroidBrowse.getAsteroids()){
            List<Asteroid.CloseApproach> closeApproaches = new LinkedList<>();
            for(Asteroid.CloseApproach closeApproach:asteroid.getCloseApproachData()){
                if(closeApproach.getCloseApproachDate().substring(0,4).equals(year)){
                    closeApproaches.add(closeApproach);
                }
            }
            if(!closeApproaches.isEmpty()){
                asteroid.setCloseApproachData(closeApproaches);
                asteroidList.add(asteroid);
            }
        }
        asteroidList.sort(Comparator.comparing(asteroid->asteroid.getEstimatedDiameter().getKilometers().getAverage()));
        Collections.reverse(asteroidList);
        if(!asteroidList.isEmpty()){
            return asteroidList.get(0);
        }
        return new Asteroid();
    }

    private  List<AsteroidDistance> getClosestAsteroidsFromList(Map<String, List<Asteroid>> asteroidDateMap) {
        List<Asteroid> asteroids = asteroidDateMap.values().stream().flatMap(Collection::stream).collect(Collectors.toCollection(LinkedList::new));
        List<AsteroidDistance> asteroidCloseApproachList = new LinkedList<>();
        for(Asteroid asteroid:asteroids){
            asteroid.getCloseApproachData().sort(Comparator.comparing(approach -> Double.parseDouble(approach.getMissDistance().getKilometers())));
            Double closestApproachKm = Double.parseDouble(asteroid.getCloseApproachData().get(0).getMissDistance().getKilometers());
            asteroidCloseApproachList.add(new AsteroidDistance(asteroid.getName(),closestApproachKm));
        }
        asteroidCloseApproachList.sort(Comparator.comparing(AsteroidDistance::getDistance));

        if(asteroidCloseApproachList.isEmpty()){
            return new LinkedList<>();
        }
        if(asteroidCloseApproachList.size()>=10){
            return asteroidCloseApproachList.subList(0,10);
        }
        return asteroidCloseApproachList.subList(0,asteroidCloseApproachList.size());
    }
}
