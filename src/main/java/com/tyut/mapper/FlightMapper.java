package com.tyut.mapper;
import java.util.List;
import com.tyut.domain.Flight;

public interface FlightMapper {
    public List <Flight> findByDepAndFin(String departureCity, String FinalCity);
    public List<Flight> findAllFlight();
}
