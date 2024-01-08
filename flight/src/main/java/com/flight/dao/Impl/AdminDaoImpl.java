package com.flight.dao.Impl;

import com.flight.dao.AdminDao;
import com.flight.domain.*;
import com.flight.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AdminDaoImpl implements AdminDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    // 查询所有的航班信息
    @Override
    public List<Flight> findAllFlight() throws Exception{
        String sql  = "select * from airline";
        List<Flight> flights = null;
        flights = template.query(sql, new BeanPropertyRowMapper<Flight>(Flight.class));

        return flights;
    }
    // 根据航班的编号flightId查询出所对应的座位信息
    @Override
    public Beat findBeatByFid(String flightId) {
        Beat beat = null;
        String sql = "select * from beat where flightId = ?";
        beat = template.queryForObject(sql, new BeanPropertyRowMapper<Beat>(Beat.class), flightId);
        return beat;
    }

    //    通过航班订单的id值获取具体的订单信息
    @Override
    public FlightOrder findFlightOrderById(int id) throws Exception{
        FlightOrder fo = null;
        String sql = "select * from flight_order where id = ?";
        fo = template.queryForObject(sql, new BeanPropertyRowMapper<FlightOrder>(FlightOrder.class), id);
        return fo;
    }

    //根据fid查找到对应的flight信息
    @Override
    public Flight findFlightById(String flightId) throws Exception{
        Flight flight = null;
        String sql = "select * from airline where flightId = ?";
        flight = template.queryForObject(sql, new BeanPropertyRowMapper<Flight>(Flight.class), flightId);

        return flight;
    }

    //根据id值查找旅行社
    @Override
    public Agency findAgencyById(String id) throws Exception{
        String sql = "select * from agency where id = ?";
        Agency agency = null;
        agency = template.queryForObject(sql, new BeanPropertyRowMapper<Agency>(Agency.class), id);
        return agency;

    }

    //根据旅客的id值查找对应的旅客信息
    @Override
    public Traveller findTravellerById(String tid) throws Exception{
        String sql = "select * from traveller where id = ?";
        Traveller traveller = null;
        traveller = template.queryForObject(sql, new BeanPropertyRowMapper<Traveller>(Traveller.class), tid);
        return traveller;
    }

    @Override
    public List<FlightOrder> findAllOrder() {
        List<FlightOrder> flightOrders = null;
        String sql = "select * from flight_order";
        try {
            flightOrders = template.query(sql, new BeanPropertyRowMapper<FlightOrder>(FlightOrder.class));
            //由已经支付的订单，则返回
            return flightOrders;
        } catch (Exception e) {
            e.printStackTrace();
            //没有支付的订单，则返回null
            return null;
        }
    }
}
