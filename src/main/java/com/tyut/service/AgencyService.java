package com.tyut.service;

import com.tyut.domain.Agency;
import com.tyut.domain.Flight;
import com.tyut.domain.Traveller;

import java.text.ParseException;
import java.util.List;

public interface AgencyService {
    public Boolean isLogin(Agency agency);
    public List<Flight> findAllFlight();

    //根据旅程的城市查找对应的航班
    List<Flight> findFlightByDepAndFinCity(String depCity,String finCity);

    boolean addFOrder(String aid, String routeId, String tid, String flightId, String beat, double price) throws ParseException;
}
