package com.example.demo.service;

import com.example.demo.model.Asteroid;
import com.example.demo.model.AsteroidBrowse;
import com.example.demo.model.AsteroidDistance;
import com.example.demo.model.AsteroidFeed;
import com.example.demo.nasaApi.NasaAsteroidApiInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AsteroidServiceTest {
    @InjectMocks
    AsteroidService asteroidService;

    @Mock
    NasaAsteroidApiInterface nasaAsteroidApi;


    @Test
    public void testGetClosestByDate(){
        AsteroidFeed asteroidFeed = new AsteroidFeed();

        Map<String,List<Asteroid>> asteroidMap = new HashMap<>();
        asteroidMap.put("2022-01-01",makeAsteroidList());

        asteroidFeed.setAsteroids(asteroidMap);
        when(nasaAsteroidApi.getAsteroidsBetweenDate(anyString(),anyString())).thenReturn(Mono.just(asteroidFeed));
        assertEquals(asteroidService.getClosestAsteroidsByDate("2015-01-01","2015-01-01").get(0).distance,3.01);


    }

    @Test
        public void testGetLargestAsteroidByYear(){
        AsteroidBrowse asteroidBrowse = new AsteroidBrowse();
        asteroidBrowse.setAsteroids(makeAsteroidList());

        when(nasaAsteroidApi.getAllAsteroids()).thenReturn(Mono.just(asteroidBrowse));
        assertEquals("asteroid2",asteroidService.getLargestAsteroidByYear("2022").getName());

        }

    private List<Asteroid> makeAsteroidList(){
        List<Asteroid> asteroidList = new LinkedList<>();

        Asteroid asteroid1 = new Asteroid();
        asteroid1.setName("asteroid1");
        Asteroid asteroid2 = new Asteroid();
        asteroid2.setName("asteroid2");
        Asteroid asteroid3 = new Asteroid();
        asteroid3.setName("asteroid3");


        List<Asteroid.CloseApproach> closeApproachList = new LinkedList<>();

        Asteroid.CloseApproach closeApproach1 = new Asteroid.CloseApproach();
        closeApproach1.setCloseApproachDate("2022-01-02");
        Asteroid.MissDistance missDistance1 = new Asteroid.MissDistance();
        missDistance1.setKilometers("5.01");
        closeApproach1.setMissDistance(missDistance1);

        Asteroid.CloseApproach closeApproach2 = new Asteroid.CloseApproach();
        closeApproach2.setCloseApproachDate("2022-01-03");
        Asteroid.MissDistance missDistance2 = new Asteroid.MissDistance();
        missDistance2.setKilometers("3.01");
        closeApproach2.setMissDistance(missDistance2);

        Asteroid.CloseApproach closeApproach3 = new Asteroid.CloseApproach();
        closeApproach3.setCloseApproachDate("2022-01-04");
        Asteroid.MissDistance missDistance3 = new Asteroid.MissDistance();
        missDistance3.setKilometers("5.01");
        closeApproach3.setMissDistance(missDistance3);

        closeApproachList.add(closeApproach1);
        closeApproachList.add(closeApproach2);
        closeApproachList.add(closeApproach3);

        Asteroid.EstimatedDiameter estimatedDiameter1 = new Asteroid.EstimatedDiameter();
        Asteroid.EstimatedDiameter.EstimatedDiameterUnit estimatedDiameterUnit1 = new Asteroid.EstimatedDiameter.EstimatedDiameterUnit();
        estimatedDiameterUnit1.setEstimatedDiameterMax("25");
        estimatedDiameterUnit1.setEstimatedDiameterMin("26");
        estimatedDiameter1.setKilometers(estimatedDiameterUnit1);
        asteroid1.setEstimatedDiameter(estimatedDiameter1);

        Asteroid.EstimatedDiameter estimatedDiameter2 = new Asteroid.EstimatedDiameter();
        Asteroid.EstimatedDiameter.EstimatedDiameterUnit estimatedDiameterUnit2 = new Asteroid.EstimatedDiameter.EstimatedDiameterUnit();
        estimatedDiameterUnit2.setEstimatedDiameterMax("35");
        estimatedDiameterUnit2.setEstimatedDiameterMin("36");
        estimatedDiameter2.setKilometers(estimatedDiameterUnit2);
        asteroid2.setEstimatedDiameter(estimatedDiameter2);

        Asteroid.EstimatedDiameter estimatedDiameter3 = new Asteroid.EstimatedDiameter();
        Asteroid.EstimatedDiameter.EstimatedDiameterUnit estimatedDiameterUnit3 = new Asteroid.EstimatedDiameter.EstimatedDiameterUnit();
        estimatedDiameterUnit3.setEstimatedDiameterMax("15");
        estimatedDiameterUnit3.setEstimatedDiameterMin("16");
        estimatedDiameter3.setKilometers(estimatedDiameterUnit3);
        asteroid3.setEstimatedDiameter(estimatedDiameter3);

        asteroid1.setCloseApproachData(closeApproachList);
        asteroid2.setCloseApproachData(closeApproachList);
        asteroid3.setCloseApproachData(closeApproachList);

        asteroidList.add(asteroid1);
        asteroidList.add(asteroid2);
        asteroidList.add(asteroid3);

        return asteroidList;
    }
}
