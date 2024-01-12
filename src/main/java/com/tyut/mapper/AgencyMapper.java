package com.tyut.mapper;

import com.tyut.domain.*;

import java.sql.Date;
import java.util.List;

public interface AgencyMapper {
    public Agency selectPassword(String agencyName);
    public List<Flight> findAllFlight();
    public List<Flight> findFlightByCity(String depCity);
    public void handlerAOrder(String aid, String routeId, int tid, Date handlerTime);
    public boolean addOrder(String aid, String routeId, String tid, String flightId, String beat, double price);
    public Flight findFlightById(String fid);
    public FlightOrder findFOrderByIds(String aid, String routeId, int tid, String flightId);
    public boolean addFOrder(String aid, String routeId, int tid, String flightId, String beat, double price);
    public void subFirstBeatCount(String flightId);
    public void subBusinessBeatCount(String flightId);
    public void subEconomyBeatCount(String flightId);
    public List<FlightOrder> findFOrder(String aid);
    public Traveller findTraById(Integer tid);
    public AgencyOrder findAgencyOrder(String aid, Integer tid, String rid);
    public AgencyOrder findOrderById(String aid, String routeId, Integer tid);
    public Agency findAgencyById(String id);
    public List<Route> findRouteByAid(String id);
    public Route findRoute(String routeId, String aid);
    public List<Route> findRouteByAidAndTid(String aid, String tid);
    public List<Traveller> findTids(String aid);
    public Beat findBeatByFid(String flightId);
    public List<Route> findRidsByTidAndAid(Integer tid, String aid);
}
