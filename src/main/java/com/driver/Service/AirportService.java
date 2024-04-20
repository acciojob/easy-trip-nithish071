package com.driver.Service;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.repository.AirportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AirportService {
    @Autowired
    private AirportRepo airportRepo;
    public void addAirport(Airport airport) {
        this.airportRepo.save(airport);
    }

    public String getLargestAirport() {
        List<Airport> airports = this.airportRepo.getAllAirports();
        List<String> highestTerminal = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for(Airport air:airports){
            max = Math.max(max,air.getNoOfTerminals());
        }
        for(Airport air:airports){
            if(air.getNoOfTerminals() == max){
                highestTerminal.add(air.getAirportName());
            }
        }
        Collections.sort(highestTerminal);
        return highestTerminal.get(0);
    }

    public City getCity(String airportName) {
        return this.airportRepo.findByname(airportName).getCity();
    }
    public String getAirportName(City city){
        List<Airport> airports = this.airportRepo.getAllAirports();
        for(Airport a:airports){
            if(a.getCity() == city){
                return a.getAirportName();
            }
        }
        return null;
    }
}
