package com.flight.dao;

import com.flight.domain.*;

import java.util.List;

public interface AdminDao {
    // 查询所有的航班信息
    List<Flight> findAllFlight() throws Exception;
    // 查询航班对应的座位
    Beat findBeatByFid(String flightId);

    FlightOrder findFlightOrderById(int parseInt) throws Exception;

    Flight findFlightById(String flightId) throws Exception;

    Agency findAgencyById(String aid) throws Exception;

    Traveller findTravellerById(String toString) throws Exception;
    // 查询所有的订单
    List<FlightOrder> findAllOrder();
}
