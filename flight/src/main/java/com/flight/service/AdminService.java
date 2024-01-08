package com.flight.service;

import com.flight.domain.Flight;
import com.flight.domain.FlightOrder;

import java.util.List;

public interface AdminService {
    // 查询所有的航班信息
    List<Flight> findAllFlight() throws Exception;
    // 获取订单列表
    FlightOrder findFlightOrderById(String id) throws Exception;
    // 查询所有的订单
    List<FlightOrder> findAllOrder() throws Exception;
}
