package com.tyut.service.Impl;

import com.tyut.domain.*;
import com.tyut.domain.Admin;
import com.tyut.mapper.AdminMapper;
import com.tyut.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Boolean isLogin(Admin admin) {
        Admin admin_1 = adminMapper.selectPassword(admin);
        if (admin_1 != null){
            if(admin.getPassword().equals(admin_1.getPassword()) ) return true;
            else return false;
        }
        else return false;
    }
    // 获取所有的航班信息
    @Override
    public List<Flight> findAllFlight() throws Exception {
        List<Flight> flights = null;
        Beat beat = null;
        //1.先查询出所有的航班信息
        flights = adminMapper.findAllFlight();
        //2.根据航班的编号flightId查询出所对应的座位信息
        for(Flight flight:flights){
            beat = adminMapper.findBeatByFid(flight.getFlightId());
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
        fo = adminMapper.findFlightOrderById(Integer.parseInt(id));

        flight = adminMapper.findFlightById(fo.getFlightId());
        fo.setFlight(flight);

        agency = adminMapper.findAgencyById(fo.getAid());
        fo.setAgency(agency);

        traveller = adminMapper.findTravellerById(fo.getTid().toString());
        System.out.println(traveller.toString());
        fo.setTraveller(traveller);
        return fo;
    }
    // 查询所有的订单
    @Override
    public List<FlightOrder> findAllOrder() throws Exception {
        Flight flight = null;
        //1.查找已经支付的航班订单的信息
        List<FlightOrder> flightOrders = adminMapper.findAllOrder();
        //1.1 如果尚没有产生的订单，则返回null
        if(flightOrders == null){
            return null;
        }
        //2.根据查找到了航班订单得到 航班的详情信息 flight 、agency、route
        for(FlightOrder fo:flightOrders){
            flight = adminMapper.findFlightById(fo.getFlightId());
            fo.setFlight(flight);
        }
        return flightOrders;
    }
    //查询航班对应的座位
    @Override
    public Beat findBeatByFid(String flightId) {
        //根据航班编号查询该航班的信息
        Beat beat = adminMapper.findBeatByFid(flightId);
        return beat;
    }
    //查询航班信息
    @Override
    public Flight findFlightById(String flightId) throws Exception {
        //根据编号查询航班信息
        Flight flight = adminMapper.findFlightById(flightId);
        return flight;
    }
    //查询旅行社信息
    @Override
    public Agency findAgencyById(String aid) throws Exception {
        Agency agency = adminMapper.findAgencyById(aid);
        return agency;
    }
    //查询旅客信息
    @Override
    public Traveller findTravellerById(String tid) throws Exception {
        Traveller traveller = adminMapper.findTravellerById(tid);
        return traveller;
    }
}
