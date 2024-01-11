package com.flight.service;

import com.flight.domain.Flight;
import com.flight.domain.FlightOrder;
import com.flight.domain.Traveller;
import com.flight.domain.AgencyOrder;
import java.util.List;

public interface AgencyService {
    //根据agencyId查找对应的游客
    List<Traveller> findAllTraveller(String id) throws Exception;
    //旅行社 查询 旅客选择的旅行社的路线 的订单 的详情
    AgencyOrder findAgencyOrderByIds(Integer tid, String aid, String routeId) throws Exception;

    //根据 tid 和 fid 查找到对应的信息
    Flight findFlightDetail(String fid, String tid) throws Exception;
//    根据对应的routeId，aid, tid,fid查找唯一的订单
    FlightOrder findFOrderByIds(String aid, String routeId, String tid, String flightId);
    //产生新的flightOrder订单
    boolean addFOrder(String aid, String routeId, String tid, String flightId, String beat, double price) throws Exception;
    //获取aid,通过aid 查找flightOrder 获取该旅行社对应的相关的航班信息
    List<FlightOrder> findFOrder(String aid) throws Exception;
}
