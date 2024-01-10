package com.tyut.mapper;
import java.util.List;
import com.tyut.domain.Flight;

public interface FlightMapper {
    public Flight findByDepCityAndFinCity(String departureCity, String FinalCity);
    public List<Flight> findAllFlight();
}
