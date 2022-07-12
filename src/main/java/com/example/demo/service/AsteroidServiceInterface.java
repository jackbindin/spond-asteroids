package com.example.demo.service;

import com.example.demo.model.Asteroid;
import com.example.demo.model.AsteroidBrowse;
import com.example.demo.model.AsteroidDistance;
import com.example.demo.model.AsteroidFeed;

import java.util.*;

public interface AsteroidServiceInterface {
    Asteroid getAsteroidById(String id);
    List<AsteroidDistance> getClosestAsteroidsByDate(String start, String end);
    Asteroid getLargestAsteroidByYear(String year);
}
