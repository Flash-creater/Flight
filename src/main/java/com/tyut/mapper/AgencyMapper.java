package com.tyut.mapper;

import com.tyut.domain.Agency;
import com.tyut.domain.Flight;

import java.sql.Date;
import java.util.List;

public interface AgencyMapper {
    public Agency selectPassword(String agencyName);
    public List<Flight> findAllFlight();
    public List<Flight> findFlightByDepAndFinCity(String depCity,String finCity);
    public void handlerAOrder(String aid, String routeId, int tid, Date handlerTime);
}
