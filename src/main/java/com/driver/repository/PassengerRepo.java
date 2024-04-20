package com.driver.repository;

import com.driver.model.Passenger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class PassengerRepo {
    private HashMap<Integer, Passenger> passengerHashMap = new HashMap<>();
    private HashMap<Integer, List<List<Integer>>> passengerBooking = new HashMap<>();

    public void addPassenger(Passenger passenger) {
        passengerHashMap.put(passenger.getPassengerId(),passenger);
    }

    public List<List<Integer>> passengerBookingDetails(Integer passengerId){
        return passengerBooking.get(passengerId);
    }

    public void addPassengersBooking(int flightId,int passengerId,int price){
        if(!passengerBooking.containsKey(passengerId)){
            List<List<Integer>> list = new ArrayList<>();
            List<Integer> l = new ArrayList<>();
            l.add(flightId);
            l.add(price);
            list.add(l);
            passengerBooking.put(passengerId,list);
        } else {
            List<Integer> l = new ArrayList<>();
            l.add(flightId);
            l.add(price);
            passengerBooking.get(passengerId).add(l);
        }
    }
}
