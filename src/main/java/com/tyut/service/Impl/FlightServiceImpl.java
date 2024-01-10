package com.tyut.service.Impl;

import com.tyut.domain.Flight;
import com.tyut.mapper.FlightMapper;
import com.tyut.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightMapper flightMapper;
    public List<Flight> findAllFlight(){
        return flightMapper.findAllFlight();
    }

    public List <Flight> findByDepAndFin(String departureCity, String FinalCity){
        return flightMapper.findByDepAndFin(departureCity,FinalCity);
    }
}
