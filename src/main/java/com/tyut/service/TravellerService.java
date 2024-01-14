package com.tyut.service;

import com.tyut.domain.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface TravellerService {
    //根据Email和password查看登录
    public Boolean isLogin(Traveller traveller);
    //添加注册用户
    public int addTraveller(Traveller traveller);
    //通过邮箱查找
    Traveller findByEmail(String email);
    //通过用户id和密码查找用户
    Traveller checkPassword(String id, String password);
    //
    boolean modifyPassword(String id, String newPassword);
    //保存用户
    boolean save(Traveller saveUser);
    //查找所有的旅行社
    List<Agency> findAllAgency();
    //根据id值查询旅行社
    Agency findAgencyById(String id) throws Exception;
    //通过agency的id值查找对应的路线
    List<Route> findRouteByAid(String id);
    //通过agency的id值和route的id值查找对应的路线
    Route findRoute(String routeId, String aid);
    //调用Service查找身份证号是否已经与有人使用
    Traveller findTraveller(String trueName, String phone, String sex, String id_card);
    //根据对应的routeId，aid, tid查找唯一的订单
    AgencyOrder findOrderById(String aid, String routeId, Integer id);
    //根据对应的routeId，aid, tid产生唯一的订单
    boolean addOrder(String aid, String routeId, Integer id, String beat) throws ParseException;
    //产生订单之后，减少卡内余额
    void subBalance(Traveller traveller, double price);
    //根据用户id查找对应的旅行社，旅行路线
    List<Agency> findAgencyOrder(String tid) throws Exception;
    //根据routeId tid aid 获取相应的路线信息
    AgencyOrder findAgencyOrderByIds(String tid, String aid, String routeId) throws Exception;
    //当退订成功之后，增加旅客的余额
    void addBalance(Traveller traveller, double price) throws Exception;
    //通过对应的Id查找旅客
    Traveller findTravellerById(String tid) throws Exception;
    //通过对应的tid aid routeId 实现退订
    boolean deleteAgencyOrder(String tid, String aid, String routeId);
    //查找所有的航班信息
    List<Flight> findAllFlight() throws Exception;
    //查找已经支付的订单
    List<FlightOrder> findPayOrder(String tid) throws Exception;
    //查找未支付的订单
    List<FlightOrder> findNOPayOrder(String tid) throws Exception;
    //    通过航班订单的id值获取具体的订单信息
    FlightOrder findFlightOrderById(String id) throws Exception;
    //更改订单的支付状态，并将用户的金额数减少
    void updateFOrder(String id) throws Exception;
    // 根据id值删除对应的flightOrder订单
    void deleteFlightOrder(String id) throws Exception;
}
