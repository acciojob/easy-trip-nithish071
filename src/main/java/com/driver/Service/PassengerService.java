package com.driver.Service;

import com.driver.model.Passenger;
import com.driver.repository.PassengerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepo passengerRepo;

    public void addPassenger(Passenger passenger) {
        this.passengerRepo.addPassenger(passenger);
    }

    public void addBooking(Integer passengerId, Integer flightId, int price) {
        this.passengerRepo.addPassengersBooking(flightId,passengerId,price);
    }

    public int getBookingCount(Integer passengerId) {
        return this.passengerRepo.passengerBookingDetails(passengerId).size();
    }
}
