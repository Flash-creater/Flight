package com.tyut.service.Impl;

import com.tyut.domain.*;
import com.tyut.mapper.AgencyMapper;
import com.tyut.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class AgencyServiceImpl implements AgencyService {

    @Autowired
    private AgencyMapper agencyMapper;
    private Agency agency;
    @Override
    public Boolean isLogin(Agency agency) {
        Agency agency1 = agencyMapper.selectPassword(agency.getAgencyName());
        if (agency1.getPassword().equals(agency.getPassword())) return true;
        else return false;
    }
    //查询所有的航班信息
    @Override
    public List<Flight> findAllFlight()  {
        List<Flight> flights = null;
        Beat beat = null;
        //1.先查询出所有的航班信息
        flights = agencyMapper.findAllFlight();
        //2.根据航班的编号flightId查询出所对应的座位信息
        for(Flight flight:flights){
            beat = agencyMapper.findBeatByFid(flight.getFlightId());
            flight.setBeat(beat);
        }
        //3.返回list
        return flights;
    }
    //根据出发的城市查找对应的航班
    @Override
    public List<Flight> findFlightByCity(String city){
        List<Flight> flights = null;
        Beat beat = null;
        //1.先查询出所有的航班信息
        flights = agencyMapper.findFlightByCity(city);
        //2.根据航班的编号flightId查询出所对应的座位信息
        for(Flight flight:flights){
            beat = agencyMapper.findBeatByFid(flight.getFlightId());
            flight.setBeat(beat);
        }
        //3.返回list
        return flights;
    }
    //根据 tid 和 fid 查找到对应的信息
    @Override
    public Flight findFlightDetail(String fid, String tid) {
        Flight flight = null;
        //查找到对应的旅客和座位信息
        Traveller traveller = agencyMapper.findTraById(Integer.parseInt(tid));
        Beat beat = agencyMapper.findBeatByFid(fid);
        //查找到对应的flight信息
        flight = agencyMapper.findFlightById(fid);
        flight.setBeat(beat);
        flight.setTraveller(traveller);
        return flight;
    }
    //    根据对应的routeId，aid, tid,fid查找唯一的订单
    @Override
    public FlightOrder findFOrderByIds(String aid, String routeId, String tid, String flightId) {
        return agencyMapper.findFOrderByIds(aid, routeId, Integer.parseInt(tid), flightId);
    }
    //产生新的flightOrder订单
    @Override
    public boolean addFOrder(String aid, String routeId, String tid, String flightId, String beat, double price) throws Exception {
        //1.修改对应的agencyOrder中的orderStatus 和 handlerTime
        //1.1 生成此时的处理时间handlerTime
        Date handlerTime = new Date();
        SimpleDateFormat df;
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = df.format(handlerTime);
        //1.2 对agencyOrder中的orderStatus 和 handlerTime进行修改
        agencyMapper.handlerAOrder(aid, routeId, Integer.parseInt(tid), df.parse(time));
        //2.添加flightOrder的订单
        boolean isOk = agencyMapper.addFOrder(aid, routeId, Integer.parseInt(tid), flightId, beat, price);
        //2.1减少航班对应舱位的座位数
        if("头等舱".equals(beat)){
            //减少对应得头等舱的座位数
            agencyMapper.subFirstBeatCount(flightId);
        }else if("商务舱".equals(beat)){
            //减少商务舱的个数
            agencyMapper.subBusinessBeatCount(flightId);
        }else if("经济舱".equals(beat)){
            //减少对应的经济舱的个数
            agencyMapper.subEconomyBeatCount(flightId);
        }
        return isOk;
    }
    //    获取aid,通过aid 查找flightOrder 获取该旅行社对应的相关的航班信息
    @Override
    public List<FlightOrder> findFOrder(String aid) {
        List<FlightOrder> flightOrderList = new ArrayList<>();
        Traveller traveller = null;
        Flight flight = null;
        AgencyOrder ao = null;

        flightOrderList = agencyMapper.findFOrder(aid);
        //如果尚未订单，则返回空
        if(flightOrderList == null ){
            return null;
        }
        //根据查找到的flightOrder来查找对应的traveller 和 flight的 信息
        for(FlightOrder fo:flightOrderList){
            flight = agencyMapper.findFlightById(fo.getFlightId());
            fo.setFlight(flight);

            traveller = agencyMapper.findTraById(fo.getTid());
            fo.setTraveller(traveller);

            //根据 aid tid rid 查找对应的订单的处理时间
            ao = agencyMapper.findAgencyOrder(aid, fo.getTid(), fo.getRouteId());
            fo.setAgencyOrder(ao);

        }

        return flightOrderList;
    }

    //根据agencyId查找对应的游客
    @Override
    public List<Traveller> findAllTraveller(String aid){
        //根据agencyId查找对应的游客id
        List<Traveller> tids = agencyMapper.findTids(aid);
        List<Traveller> travellers = new ArrayList<>();
        List<Route> routes = new ArrayList<>();
        AgencyOrder agencyOrder = null;
        //根据查询到的tid查找对应的traveller的信息
        Traveller traveller = null;
        for(Traveller tid:tids){
            traveller = agencyMapper.findTraById(tid.getId());
            //根据tid和aid查询出对应的路线id
            routes = agencyMapper.findRidsByTidAndAid(tid.getId(), aid);
            //根据rid tid aid 查找到对应的订单信息
            for(Route route:routes){
                agencyOrder = agencyMapper.findAgencyOrder(aid, tid.getId(), route.getRouteId());
                route.setAgencyOrder(agencyOrder);
            }
            traveller.setRouteList(routes);
            travellers.add(traveller);
        }
        return travellers;
    }
    //旅行社 查询 旅客选择的旅行社的路线 的订单 的详情
    @Override
    public AgencyOrder findAgencyOrderByIds(Integer tid, String aid, String routeId){
        AgencyOrder ao = null;
        Route route = null;
        Traveller traveller = null;
        //查找出对应的游客信息
        traveller = agencyMapper.findTraById(tid);
        //查找出对应的旅行路线信息
        route = agencyMapper.findRoute(routeId, aid);
        System.out.println(route.toString());
        //查找出对应的旅行社订单信息 包括下单时间 状态 处理时间
        ao = agencyMapper.findAgencyOrder(aid, tid, routeId);
        ao.setTraveller(traveller);
        ao.setRoute(route);

        return ao;
    }
}
