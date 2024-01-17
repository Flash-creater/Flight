package com.tyut.service;

import com.tyut.domain.Admin;
import com.tyut.domain.*;

import java.util.List;

public interface AdminService {
     public Boolean isLogin(Admin admin);
     // 查询所有的航班信息
     List<Flight> findAllFlight() throws Exception;
     // 获取订单列表
     FlightOrder findFlightOrderById(String id) throws Exception;
     // 查询所有的订单
     List<FlightOrder> findAllOrder() throws Exception;
     // 查询航班对应的座位
     Beat findBeatByFid(String flightId);
     // 查询航班信息
     Flight findFlightById(String id) throws Exception;
     // 查询旅行社信息
     Agency findAgencyById(String aid) throws Exception;
     // 查询旅客信息
     Traveller findTravellerById(String toString) throws Exception;
}
