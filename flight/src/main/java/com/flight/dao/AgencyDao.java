package com.flight.dao;

import com.flight.domain.*;

import java.util.Date;
import java.util.List;

public interface AgencyDao {
    //查询所有的航班信息
    List<Flight> findAllFlight() throws Exception;
    //2.根据航班的编号flightId查询出所对应的座位信息
    Beat findBeatByFid(String flightId) throws Exception;
    //根据agencyId查找对应的游客
    List<Traveller> findAllTraveller(String id) throws Exception;
    //根据agencyId查找对应的游客id
    List<Traveller> findTids(String aid) throws Exception;
    //根据查询到的tid查找对应的traveller的信息
    Traveller findTraById(Integer tid) throws Exception;
    //通过agency的id值查找对应的旅行社
    Agency findAgencyById(String id) throws Exception;
    //通过agency的id值查找对应的路线
    List<Route> findRouteByAid(String id);
    //通过agency的id值和route的id值查找对应的路线
    Route findRoute(String routeId, String aid);
    //根据对应的routeId，aid, tid查找唯一的订单
    AgencyOrder findOrderById(String aid, String routeId, Integer tid);
    //根据aid和tid查询出对应的所有的路线id
    List<Route> findRidsByTidAndAid(Integer tid, String aid)throws Exception;
    //根据tid,rid,aid查询出该订单对应的下单时间，下单状态，以及处理订单的时间
    AgencyOrder findAgencyOrder(String aid, Integer tid, String rid) throws Exception;
    //根据tid和aid查询出对应的所有的路线id
    List<Route> findRouteByAidAndTid(String aid, String tid) throws Exception;
    //根据出发的城市查找对应的航班
    List<Flight> findFlightByCity(String city) throws Exception;
    //根据fid查找到对应的flight
    Flight findFlightById(String fid) throws Exception;
//    根据对应的routeId，aid, tid,fid查找唯一的订单
    FlightOrder findFOrderByIds(String aid, String routeId, int tid, String flightId);
    //对agencyOrder中的orderStatus 和 handlerTime进行修改
    void handlerAOrder(String aid, String routeId, int tid, Date handlerTime) throws Exception;
    //添加flightOrder的订单
    boolean addFOrder(String aid, String routeId, int parseInt, String flightId, String beat, double price);
    //减少航班对应舱位的座位数
    void subBusinessBeatCount(String flightId) throws Exception;
    void subFirstBeatCount(String flightId) throws Exception;
    void subEconomyBeatCount(String flightId) throws Exception;
    //根据aid查找对应的flightOrder订单
    List<FlightOrder> findFOrder(String aid) throws Exception;


}
