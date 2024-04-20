package com.driver.Service;

import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.repository.AirportRepo;
import com.driver.repository.FlightRepo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class FlightService {
    @Autowired
    private FlightRepo flightRepo;
    public void addFlight(Flight flight) {
        this.flightRepo.save(flight);
    }

    public int calculate(Integer flightId) {
        int c = this.flightRepo.passengerDetails(flightId).size();
        return 3000 + (c*50);
    }

    public double getShortestDuration(City fromCity, City toCity) {
        List<Flight> flightList = this.flightRepo.getallFlight();
        List<Flight> flights = new ArrayList<>();
        boolean flag = false;
        for(Flight flight:flightList){
            if(flight.getFromCity() == fromCity && flight.getToCity() == toCity){
                flights.add(flight);
                flag = true;
            }
        }
        if(flag == false){
            return -1;
        }
        Collections.sort(flights,(a,b)-> (int) (a.getDuration() - b.getDuration()));
        return flights.get(0).getDuration();
    }

    public int getNumberOfPeople(Date date, City city) {
        List<Flight> flights = this.flightRepo.getallFlight();

        int count = 0;
        for(Flight f:flights){
            if(f.getFromCity() == city && f.getFlightDate() == date){
                int c = this.flightRepo.passengerDetails(f.getFlightId()).size();
                count += c;
            }
        }
        return count;
    }

    public void addPassengers(int flighId,int passengerId,int price){
        Flight f = this.flightRepo.getFlightById(flighId);
        this.flightRepo.addPassengers(flighId,passengerId,price);
    }

    public boolean book(Integer flightId, Integer passengerId) {
        Flight flight = this.flightRepo.getFlightById(flightId);
        int c = this.flightRepo.passengerDetails(flightId).size();
        if(c >= flight.getMaxCapacity()){
            return false;
        }
        List<List<Integer>> passengers = this.flightRepo.passengerDetails(flightId);
        for(List<Integer> l: passengers){
            if(l.get(0) == passengerId){
                return false;
            }
        }
        int fare = this.calculate(flightId);
        this.addPassengers(flightId,passengerId,fare);
        return true;
    }

    public boolean cancelTicket(Integer flightId, Integer passengerId) {
        List<List<Integer>> passengers = this.flightRepo.passengerDetails(flightId);
        boolean flag = false;
        for(List<Integer> l: passengers){
            if(l.get(0) == passengerId){
                flag = true;
                break;
            }
        }
        if(flag == false){
            return false;
        }
        this.flightRepo.removePassenger(flightId,passengerId);
        return true;
    }

    public City getCity(Integer flightId) {
        Flight f = this.flightRepo.getFlightById(flightId);
        return f.getFromCity();
    }

    public int calculateRevenue(Integer flightId) {
        int revenue = 0;
        List<List<Integer>> l = this.flightRepo.passengerDetails(flightId);
        for(List<Integer> a:l){
            revenue += a.get(1);
        }
        return revenue;
    }
}
