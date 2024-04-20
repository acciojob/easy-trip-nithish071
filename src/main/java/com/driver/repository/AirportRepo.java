package com.driver.repository;

import com.driver.controllers.AirportController;
import com.driver.model.Airport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Repository
public class AirportRepo {

    HashMap<String, Airport> airports = new HashMap<>();


    public void save(Airport airport) {
        String name = airport.getAirportName();
        airports.put(name,airport);
    }

    public List<Airport> getAllAirports(){
        return new ArrayList<>(airports.values());
    }
    public Airport findByname(String name){
        return airports.get(name);
    }
}
