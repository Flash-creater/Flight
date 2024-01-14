package com.tyut.service.Impl;

import com.tyut.domain.*;
import com.tyut.mapper.TravellerMapper;
import com.tyut.service.TravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TravellerServiceImpl implements TravellerService {
    @Autowired
    private TravellerMapper travellerMapper;
    @Override
    public Boolean isLogin(Traveller traveller) {
        Traveller traveller1 = travellerMapper.selectPassword(traveller);
        if (traveller1 != null ){
            if (traveller1.getPassword().equals(traveller.getPassword())) return true;
            else return false;
        }
        else {
            return false;
        }

    }

    @Override
    public int addTraveller(Traveller traveller) {
        return travellerMapper.addTraveller(traveller);
    }

    @Override
    public Traveller findByEmail(String email) {
        return travellerMapper.findByEmail(email);
    }

    @Override
    public Traveller checkPassword(String id, String password) {
        return travellerMapper.checkPassword(id,password);
    }

    @Override
    public boolean modifyPassword(String id, String newPassword) {
        return travellerMapper.modifyPassword(Integer.parseInt(id),newPassword);
    }

    @Override
    public boolean save(Traveller saveUser) {
        return travellerMapper.save(saveUser);
    }
    //查找所有的旅行社
    @Override
    public List<Agency> findAllAgency() {
        return travellerMapper.findAllAgency();
    }
    //根据id值查询旅行社
    @Override
    public Agency findAgencyById(String id) throws Exception{
        return travellerMapper.findAgencyById(id);
    }
    //通过agency的id值查找对应的路线
    @Override
    public List<Route> findRouteByAid(String aid) {
        return travellerMapper.findRouteByAid(aid);
    }
    //通过agency的id值和route的id值查找对应的路线
    @Override
    public Route findRoute(String routeId, String aid) {
        return travellerMapper.findRoute(routeId, aid);
    }
    //调用Service查找身份证号是否已经与有人使用
    @Override
    public Traveller findTraveller(String trueName, String phone, String sex, String id_card) {
        return travellerMapper.findTraveller(trueName, phone, sex, id_card);
    }
    //根据对应的routeId，aid, tid查找唯一的订单
    @Override
    public AgencyOrder findOrderById(String aid, String routeId, Integer tid) {

        return travellerMapper.findOrderById(aid, routeId, tid);
    }
    //根据对应的routeId，aid, tid产生唯一的订单
    @Override
    public boolean addOrder(String aid, String routeId, Integer tid, String beat) throws ParseException {
        //生成下单时间，
        Date orderTime = new Date();
        SimpleDateFormat df;
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = df.format(orderTime);

        return travellerMapper.addOrder(aid, routeId, tid, df.parse(time), beat);
    }
    //减少订单对应的价格数
    @Override
    public void subBalance(Traveller traveller, double price) throws Exception {
        Traveller traveller1 = travellerMapper.findTravellerById(String.valueOf(traveller.getId()));
        Double newBalance = traveller1.getBalance()-price;
        traveller.setBalance(newBalance);
        travellerMapper.subBalance(traveller);
    }
    //根据用户id查找对应的旅行社，旅行路线
    @Override
    public List<Agency> findAgencyOrder(String tid) throws Exception{
        //查询出旅客选取的旅行社的id
        Agency agency = null;
        AgencyOrder ao =null;
        List<Agency> aids = travellerMapper.findAidsByTid(tid);

        List<Agency> agencies = new ArrayList<Agency>();
        List<Route> routes = new ArrayList<Route>();
        for(Agency aid:aids){
            //根据查询出的aids查找出所有的旅行社的信息
            agency = travellerMapper.findAgencyById(aid.getId());
            //根据tid和aid查询出对应的所有的路线id
            routes = travellerMapper.findRouteByAidAndTid(aid.getId(), tid);
            for(Route route:routes){
                //根据aid tid rid 查找出唯一的agencyOrder
                ao = travellerMapper.findAgencyOrder(aid.getId(), tid, route.getRouteId());
                route.setAgencyOrder(ao);
            }
            agency.setRouteList(routes);
            agencies.add(agency);
        }
        return agencies;
    }
    //根据routeId tid aid 获取相应的路线信息
    @Override
    public AgencyOrder findAgencyOrderByIds(String tid, String aid, String routeId) throws Exception {
        AgencyOrder ao = null;
        Agency agency = null;
        Route route = null;

        //查找出对应的旅行社信息
        agency = travellerMapper.findAgencyById(aid);
        //查找出对应的旅行路线信息
        route = travellerMapper.findRoute(routeId, aid);
        System.out.println(route.toString());
        //查找出对应的旅行社订单信息 包括下单时间 状态 处理时间
        ao = travellerMapper.findAgencyOrder(aid, tid, routeId);
        ao.setAgency(agency);
        ao.setRoute(route);

        return ao;
    }
    //当退订成功之后，增加旅客的余额
    @Override
    public void addBalance(Traveller traveller, double price) throws Exception{
        Traveller traveller1 = travellerMapper.findTravellerById(String.valueOf(traveller.getId()));
        Double newBalance = traveller1.getBalance()+price;
        traveller.setBalance(newBalance);
        travellerMapper.addBalance(traveller);
    }

    @Override
    public Traveller findTravellerById(String tid) throws Exception{
        return travellerMapper.findTravellerById(tid);
    }
    //通过对应的tid aid routeId 实现退订
    @Override
    public boolean deleteAgencyOrder(String tid, String aid, String routeId) {
        return travellerMapper.deleteAgencyOrder(tid, aid, routeId);
    }
    //查询所有的航班信息
    @Override
    public List<Flight> findAllFlight() throws Exception {
        List<Flight> flights ;
        Beat beat ;
        //1.先查询出所有的航班信息
        flights = travellerMapper.findAllFlight();
        //2.根据航班的编号flightId查询出所对应的座位信息
        for(Flight flight:flights){
            beat = travellerMapper.findBeatByFid(flight.getFlightId());
            flight.setBeat(beat);
        }
        //3.返回list
        return flights;
    }
    //查找已经支付的订单
    @Override
    public List<FlightOrder> findPayOrder(String tid) throws Exception {
        Flight flight = null;
        //1.查找已经支付的航班订单的信息
        List<FlightOrder> flightOrders = travellerMapper.findPayOrder(Integer.parseInt(tid));
        //1.1 如果尚没有产生的订单，则返回null
        if(flightOrders == null){
            return null;
        }
        //2.根据查找到了航班订单得到 航班的详情信息 flight 、agency、route
        for(FlightOrder fo:flightOrders){
            flight = travellerMapper.findFlightById(fo.getFlightId());
            fo.setFlight(flight);
        }
        return flightOrders;
    }
    //查找未支付的订单
    @Override
    public List<FlightOrder> findNOPayOrder(String tid) throws Exception {
        Flight flight = null;
        //1.查找已经支付的航班订单的信息
        List<FlightOrder> flightOrders = travellerMapper.findNOPayOrder(Integer.parseInt(tid));
        //1.1 如果尚没有产生的订单，则返回null
        if(flightOrders == null){
            return null;
        }
        //2.根据查找到了航班订单得到 航班的详情信息 flight 、agency、route
        for(FlightOrder fo:flightOrders){
            flight = travellerMapper.findFlightById(fo.getFlightId());
            fo.setFlight(flight);
        }
        return flightOrders;
    }
    //    通过航班订单的id值获取具体的订单信息
    @Override
    public FlightOrder findFlightOrderById(String id) throws Exception {
        FlightOrder fo = null;
        Flight flight = null;
        Agency agency = null;
        Traveller traveller = null;
        //获取航班订单的相关信息
        fo = travellerMapper.findFlightOrderById(Integer.parseInt(id));

        flight = travellerMapper.findFlightById(fo.getFlightId());
        fo.setFlight(flight);

        agency = travellerMapper.findAgencyById(fo.getAid());
        fo.setAgency(agency);

        traveller = travellerMapper.findTravellerById(fo.getTid().toString());
        System.out.println(traveller.toString());
        fo.setTraveller(traveller);
        return fo;
    }
    //更改订单的支付状态，并将用户的金额数减少
    @Override
    public void updateFOrder(String id) throws Exception {
        //获取当前的支付时间
        Date nowTime = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = df.format(nowTime);
        //将当前的时间 设置为支付的时间 并更改支付的状态
        travellerMapper.updateFOrder(id, df.parse(time));
        //将对应的游客的金额数减少
        FlightOrder fo = travellerMapper.findFlightOrderById(Integer.parseInt(id));
        Traveller traveller = travellerMapper.findTravellerById(fo.getTid().toString());
        traveller.setBalance(traveller.getBalance()-fo.getOrderPrice());
        travellerMapper.subBalance(traveller);
    }

    // 根据id值删除对应的flightOrder订单
    @Override
    public void deleteFlightOrder(String id) throws Exception {
        // 修改旅行社的订单状态 为“已取消处理”
        FlightOrder fo = travellerMapper.findFlightOrderById(Integer.parseInt(id));
        AgencyOrder ao = travellerMapper.findAgencyOrder(fo.getAid(), fo.getTid().toString(), fo.getRouteId());
        int status = 2; // status == 2表示 “已取消处理”
        // 获取取消处理的时间
        // 更新AgencyOrder的状态  和 取消处理的时间
        //获取当前的时间
        Date nowTime = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = df.format(nowTime);
        travellerMapper.handlerAOrder(ao.getId(), status, time);
        // 删除对应的航班订单
        travellerMapper.deleteFlightOrder(id);

    }

}
