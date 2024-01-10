package com.tyut.service;

import com.tyut.domain.Flight;

import java.util.List;

public interface FlightService {
    public List<Flight> findAllFlight();
    public List <Flight> findByDepAndFin(String departureCity, String FinalCity);
}
