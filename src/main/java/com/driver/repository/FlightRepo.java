package com.driver.repository;

import com.driver.model.Flight;
import com.driver.model.Passenger;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class FlightRepo {
    private HashMap<Integer, Flight> flights = new HashMap<>();
    private HashMap<Integer, List<List<Integer>>> flighwithPassenger = new HashMap<>();

    public void save(Flight flight) {
        int flightId = flight.getFlightId();
        this.flights.put(flightId,flight);
    }

    public List<Flight> getallFlight(){
        return new ArrayList<>(flights.values());
    }
    public List<List<Integer>> passengerDetails(Integer flightId){
        if(flighwithPassenger.containsKey(flightId)){
            return flighwithPassenger.get(flightId);
        }
        return new ArrayList<>();
    }

    public void addPassengers(int flightId,int passengerId,int price){
        if(!flighwithPassenger.containsKey(flightId)){
            List<List<Integer>> list = new ArrayList<>();
            List<Integer> l = new ArrayList<>();
            l.add(passengerId);
            l.add(price);
            list.add(l);
            flighwithPassenger.put(flightId,list);
        } else {
            List<Integer> l = new ArrayList<>();
            l.add(passengerId);
            l.add(price);
            flighwithPassenger.get(flightId).add(l);
        }
    }

    public Flight getFlightById(Integer flightId){
        return flights.get(flightId);
    }

    public void removePassenger(Integer flightId, Integer passengerId) {
        List<List<Integer>> list = this.passengerDetails(flightId);
        int n = list.size();
        for(int i = 0;i < n; i++){
            if(!list.isEmpty() && list.get(i).get(0) == passengerId){
                List<Integer> remove = list.remove(i);
                break;
            }
        }
        this.flighwithPassenger.put(flightId,list);
    }
}
