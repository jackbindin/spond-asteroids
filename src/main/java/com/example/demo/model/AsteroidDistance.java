package com.example.demo.model;

import java.math.BigDecimal;

public class AsteroidDistance{

    public AsteroidDistance(String name, Double distance){
        this.name = name;
        this.distance = distance;

    }
    public String name;
    public Double distance;

    public String getName(){
        return name;
    }

    public BigDecimal getDistance(){
        return BigDecimal.valueOf(distance);
    }

}
