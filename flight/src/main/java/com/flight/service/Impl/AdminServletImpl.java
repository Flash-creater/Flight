package com.flight.service.Impl;

import com.flight.dao.AdminDao;
import com.flight.dao.Impl.AdminDaoImpl;
import com.flight.domain.*;
import com.flight.service.AdminService;

import java.util.List;

public class AdminServletImpl implements AdminService {
    private AdminDao adminDao = new AdminDaoImpl();
    // 获取所有的航班信息
    @Override
    public List<Flight> findAllFlight() throws Exception {
        List<Flight> flights = null;
        Beat beat = null;
        //1.先查询出所有的航班信息
        flights = adminDao.findAllFlight();
        //2.根据航班的编号flightId查询出所对应的座位信息
        for(Flight flight:flights){
            beat = adminDao.findBeatByFid(flight.getFlightId());
            flight.setBeat(beat);
        }
        //3.返回list
        return flights;
    }
    // 获取订单列表
    @Override
    public FlightOrder findFlightOrderById(String id) throws Exception {
        FlightOrder fo = null;
        Flight flight = null;
        Agency agency = null;
        Traveller traveller = null;
        //获取航班订单的相关信息
        fo = adminDao.findFlightOrderById(Integer.parseInt(id));

        flight = adminDao.findFlightById(fo.getFlightId());
        fo.setFlight(flight);

        agency = adminDao.findAgencyById(fo.getAid());
        fo.setAgency(agency);

        traveller = adminDao.findTravellerById(fo.getTid().toString());
        System.out.println(traveller.toString());
        fo.setTraveller(traveller);
        return fo;
    }
    // 查询所有的订单
    @Override
    public List<FlightOrder> findAllOrder() throws Exception {
        Flight flight = null;
        //1.查找已经支付的航班订单的信息
        List<FlightOrder> flightOrders = adminDao.findAllOrder();
        //1.1 如果尚没有产生的订单，则返回null
        if(flightOrders == null){
            return null;
        }
        //2.根据查找到了航班订单得到 航班的详情信息 flight 、agency、route
        for(FlightOrder fo:flightOrders){
            flight = adminDao.findFlightById(fo.getFlightId());
            fo.setFlight(flight);
        }
        return flightOrders;
    }
}
