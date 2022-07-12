package com.example.demo.controller;

import com.example.demo.model.Asteroid;
import com.example.demo.model.AsteroidDistance;
import com.example.demo.service.AsteroidServiceInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AsteroidControllerTest {
    @InjectMocks
    AsteroidController asteroidController;

    @Mock
    AsteroidServiceInterface asteroidService;

    @Test
    public void testGetAsteroidName(){
        Asteroid asteroid = makeAsteroid();

        when(asteroidService.getAsteroidById("123")).thenReturn(asteroid);
        assertEquals(asteroidController.getAsteroidName("123").getBody(),asteroid);
    }

    @Test
    public void testGetLargestAsteroidByYear(){
        Asteroid asteroid = makeAsteroid();

        when(asteroidService.getLargestAsteroidByYear("2022")).thenReturn(asteroid);
        assertEquals(asteroidController.getLargestAsteroidByYear("2022").getBody(),asteroid);

    }

    @Test
    public void  testGetClosestByDate(){
        List<AsteroidDistance> asteroidDistanceList = makeAsteroidDistances();

        when(asteroidService.getClosestAsteroidsByDate("2015-01-01","2015-01-08")).thenReturn(asteroidDistanceList);
        assertEquals(asteroidController.getClosestAsteroidsByDate("2015-01-01","2015-01-08").getBody(),asteroidDistanceList);
    }

    private Asteroid makeAsteroid(){
        Asteroid asteroid = new Asteroid();
        asteroid.setName("asteroid1");
        asteroid.setNeoReferenceid("123");
        return asteroid;
    }

    private List<AsteroidDistance> makeAsteroidDistances(){
        List<AsteroidDistance> asteroidDistanceList = new LinkedList<>();
        AsteroidDistance asteroidDistance1 = new AsteroidDistance("asteroid1",5.01);
        AsteroidDistance asteroidDistance2 = new AsteroidDistance("asteroid2",1.01);
        AsteroidDistance asteroidDistance3 = new AsteroidDistance("asteroid3",3.01);
        AsteroidDistance asteroidDistance4 = new AsteroidDistance("asteroid4",4.01);
        AsteroidDistance asteroidDistance5 = new AsteroidDistance("asteroid5",2.01);

        asteroidDistanceList.add(asteroidDistance1);
        asteroidDistanceList.add(asteroidDistance2);
        asteroidDistanceList.add(asteroidDistance3);
        asteroidDistanceList.add(asteroidDistance4);
        asteroidDistanceList.add(asteroidDistance5);
        return asteroidDistanceList;
    }
}
