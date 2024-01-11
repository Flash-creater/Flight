package com.flight.dao.Impl;

import com.flight.dao.AgencyDao;
import com.flight.domain.*;
import com.flight.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

public class AgencyDaoImpl implements AgencyDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    //查询所有的航班信息
    @Override
//    public List<Flight> findAllFlight() throws Exception{
//        String sql  = "select * from airline";
//        List<Flight> flights = null;
//        flights = template.query(sql, new BeanPropertyRowMapper<Flight>(Flight.class));
//
//        return flights;
//    }
    //根据出发的城市查找对应的航班
    @Override
//    public List<Flight> findFlightByCity(String city) throws Exception{
//        String sql  = "select * from airline where departureCity = ?";
//        List<Flight> flights = null;
//        flights = template.query(sql, new BeanPropertyRowMapper<Flight>(Flight.class), city);
//        return flights;
//    }
    //根据fid查找到对应的flight
    @Override
//    public Flight findFlightById(String fid) throws Exception{
//        String sql  = "select * from airline where flightId = ?";
//        Flight flight = null;
//        flight = template.queryForObject(sql, new BeanPropertyRowMapper<Flight>(Flight.class), fid);
//        return flight;
//    }
//    根据对应的routeId，aid, tid,fid查找唯一的订单
    @Override
//    public FlightOrder findFOrderByIds(String aid, String routeId, int tid, String flightId) {
//        FlightOrder fo = null;
//        String sql = "select * from flight_order where aid=? and routeId = ? and tid = ? and flightId = ?";
//        try {
//            fo = template.queryForObject(sql, new BeanPropertyRowMapper<FlightOrder>(FlightOrder.class),aid, routeId, tid, flightId);
//            return fo;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    //1.2 对agencyOrder中的orderStatus 和 handlerTime进行修改
    @Override
//    public void handlerAOrder(String aid, String routeId, int tid, Date handlerTime) throws Exception{
//        String sql = "update agency_order set handlerTime = ?, orderStatus = 1 where aid = ? and tid = ? and routeId = ?";
//        template.update(sql, handlerTime, aid, tid, routeId);
//    }
    //2.添加flightOrder的订单
    @Override
//    public boolean addFOrder(String aid, String routeId, int tid, String flightId, String beat, double price) {
//        String sql = "insert into flight_order(aid, tid, routeId, flightId, orderPrice, beat) values(?,?,?,?,?,?)";
//        int count = template.update(sql,aid, tid, routeId, flightId, price, beat);
//        if(count > 0){
//            return true;
//        }{
//            return false;
//        }
//    }
    //减少航班对应舱位的座位数
    @Override
//    public void subFirstBeatCount(String flightId) throws Exception{
//        String sql = "update beat set firstCount=firstCount-1 where flightId = ?";
//        template.update(sql, flightId);
//    }
    @Override
//    public void subBusinessBeatCount(String flightId) throws Exception{
//        String sql = "update beat set businessCount=businessCount-1 where flightId = ?";
//        template.update(sql, flightId);
//    }
    @Override
//    public void subEconomyBeatCount(String flightId) throws Exception{
//        String sql = "update beat set economyCount=economyCount-1 where flightId = ?";
//        template.update(sql, flightId);
//    }
    //根据aid查找对应的flightOrder订单
    @Override
//    public List<FlightOrder> findFOrder(String aid) throws Exception{
//        String sql = "select * from flight_order where aid = ?";
//        List<FlightOrder> flightOrders = null;
//        flightOrders = template.query(sql, new BeanPropertyRowMapper<FlightOrder>(FlightOrder.class), aid);
//        return flightOrders;
//    }

    //根据航班的编号flightId查询出所对应的座位信息
    @Override
    public Beat findBeatByFid(String flightId) throws Exception{
        Beat beat = null;
        String sql = "select * from beat where flightId = ?";
        beat = template.queryForObject(sql, new BeanPropertyRowMapper<Beat>(Beat.class), flightId);
        return beat;
    }
    //根据agencyId查找对应的游客
    @Override
    public List<Traveller> findAllTraveller(String id) throws Exception {
        String sql = "select *";
        return null;
    }
    //根据agencyId查找对应的游客id
    @Override
    public List<Traveller> findTids(String aid) throws Exception{
        String sql = "select * from traveller where id in (SELECT tid FROM agency_order WHERE id IN (SELECT MIN(id) FROM agency_order WHERE aid = ? GROUP BY tid))";
        List<Traveller> tids = null;
        tids = template.query(sql, new BeanPropertyRowMapper<Traveller>(Traveller.class), aid);
        return tids;
    }
    //根据查询到的tid查找对应的traveller的信息
    @Override
//    public Traveller findTraById(Integer tid) throws Exception {
//        String sql = "select * from traveller where id = ?";
//        Traveller traveller = null;
//        traveller = template.queryForObject(sql, new BeanPropertyRowMapper<Traveller>(Traveller.class), tid);
//        return traveller;
//    }
    //根据aid和tid查询出对应的所有的路线id
    @Override
    public List<Route> findRidsByTidAndAid(Integer tid, String aid) throws Exception{
        String sql = "select * from route where routeID in(SELECT routeId FROM agency_order WHERE tid = ? and aid = ?)";
        List<Route> Routes = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), tid, aid);
        return Routes;
    }
    //根据tid,rid,aid查询出该订单对应的下单时间，下单状态，以及处理订单的时间
    @Override
//    public AgencyOrder findAgencyOrder(String aid, Integer tid, String rid) throws Exception{
//        String sql = "select * from agency_order where aid=? and tid =? and routeId=?";
//        AgencyOrder ao = null;
//        ao = template.queryForObject(sql, new BeanPropertyRowMapper<AgencyOrder>(AgencyOrder.class),aid,tid,rid);
//        return ao;
//    }
    //根据tid和aid查询出对应的所有的路线id

    @Override
    public List<Route> findRouteByAidAndTid(String aid, String tid) throws Exception{
        String sql = "select * from route where routeID in (select routeId from agency_order where aid = ? and tid = ?)";
        List<Route> route =null;
        route = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), aid, tid);
        return route;
    }

    //根据对应的routeId，aid, tid查找唯一的订单
    @Override
//    public AgencyOrder findOrderById(String aid, String routeId, Integer tid) {
//        String sql = "select * from agency_order where aid=? and routeId = ? and tid= ?";
//        AgencyOrder ao = null;
//        try {
//            ao = template.queryForObject(sql, new BeanPropertyRowMapper<AgencyOrder>(AgencyOrder.class), aid, routeId, tid);
//            return ao;
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    //根据id值查找旅行社
    @Override
//    public Agency findAgencyById(String id) throws Exception{
//        String sql = "select * from agency where id = ?";
//        Agency agency = null;
//        agency = template.queryForObject(sql, new BeanPropertyRowMapper<Agency>(Agency.class), id);
//        return agency;
//
//    }
    //通过agency的id值查找对应的路线
    @Override
    public List<Route> findRouteByAid(String id) {
        String sql = "select * from route where aid = ?";
        List<Route> routes = null;
        try {
            routes = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), id);
            return routes;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    //通过agency的id值和route的id值查找对应的路线
    @Override
    public Route findRoute(String routeId, String aid) {
        String sql = "select * from route where aid = ? and routeId = ?";
        Route route = null;
        try {
            route = template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class), aid, routeId);
            return route;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
