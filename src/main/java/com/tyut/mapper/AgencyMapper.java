package com.tyut.mapper;

import com.tyut.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AgencyMapper {
    public Agency selectPassword(Agency agency);
    public List<Flight> findAllFlight();
    public List<Flight> findFlightByCities(@Param("departureCity") String departureCity, @Param("finalCity") String finalCity);
    public void handlerAOrder(@Param("aid") String aid, @Param("routeId") String routeId, @Param("tid") int tid, @Param("handlerTime") Date handlerTime);
    public boolean addOrder(@Param("aid") String aid, @Param("routeId") String routeId, @Param("tid") String tid, @Param("flightId") String flightId, @Param("beat") String beat, @Param("orderPrice") Integer orderPrice);
    public Flight findFlightById(String flightId);
    public FlightOrder findFOrderByIds(@Param("aid") String aid, @Param("routeId") String routeId, @Param("tid") int tid, @Param("flightId") String flightId);
    public boolean addFOrder(@Param("aid") String aid, @Param("routeId") String routeId, @Param("tid") int tid, @Param("flightId") String flightId, @Param("beat") String beat, @Param("orderPrice") Integer orderPrice);
    public void subFirstBeatCount(String flightId);
    public void subBusinessBeatCount(String flightId);
    public void subEconomyBeatCount(String flightId);
    public List<FlightOrder> findFOrder(String aid);
    public Traveller findTraById(Integer tid);
    public AgencyOrder findAgencyOrder(@Param("aid") String aid, @Param("tid") Integer tid, @Param("routeId") String routeId);
    public AgencyOrder findOrderById(@Param("aid") String aid, @Param("routeId") String routeId, @Param("tid") Integer tid);
    public Agency findAgencyById(String id);
    public List<Route> findRouteByAid(String id);
    public Route findRoute(@Param("routeId") String routeId, @Param("aid") String aid);
    public List<Route> findRouteByAidAndTid(String aid, String tid);
    public List<Traveller> findTids(String aid);
    public Beat findBeatByFid(String flightId);
    List<Route> findRidsByTidAndAid(@Param("tid") Integer tid, @Param("aid") String aid);
}
