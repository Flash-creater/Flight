package com.tyut.mapper;

import com.tyut.domain.*;

import java.util.List;


public interface AdminMapper {
    public Admin selectPassword(Admin admin);
    public List<Flight> findAllFlight() throws Exception;

    public Beat findBeatByFid(String flightId);

    public FlightOrder findFlightOrderById(int parseInt) throws Exception;

    public Flight findFlightById(String id) throws Exception;

    public Agency findAgencyById(String aid) throws Exception;

    public Traveller findTravellerById(String toString) throws Exception;
    // 查询所有的订单
    public List<FlightOrder> findAllOrder();
}
