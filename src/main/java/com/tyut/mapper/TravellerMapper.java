package com.tyut.mapper;


import com.tyut.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TravellerMapper {
   //查找password是否正确
    public Traveller selectPassword(Traveller traveller);
    //添加用户
    public int addTraveller(Traveller traveller);

    //通过邮箱查找
    Traveller findByEmail(String email);
    //通过用户id和密码查找用户
    Traveller checkPassword(@Param("id") String id, @Param("password") String password);

    boolean modifyPassword(@Param("id") int id, @Param("newPassword") String newPassword);
    //保存用户
    boolean save(Traveller saveUser);
    //查找所有的旅行社
    List<Agency> findAllAgency();
    //通过agency的id值查找对应的旅行社
    Agency findAgencyById(@Param("id") String id) throws Exception;
    //通过agency的id值查找对应的路线
    List<Route> findRouteByAid(@Param("aid") String aid);
    //通过agency的id值和route的id值查找对应的路线
    Route findRoute(@Param("routeId") String routeId, @Param("aid") String aid);
    //调用Service查找身份证号是否已经与有人使用
    Traveller findTraveller(@Param("trueName") String trueName, @Param("phone") String phone, @Param("sex") String sex, @Param("id_card") String id_card);
    //根据对应的routeId，aid, tid查找唯一的订单
    AgencyOrder findOrderById(@Param("aid") String aid,@Param("routeId") String routeId, @Param("tid") Integer tid);
    //根据对应的routeId，aid, tid产生唯一的订单,已经下单的时间
    boolean addOrder(@Param("aid") String aid, @Param("routeId") String routeId, @Param("tid") Integer tid, @Param("parse") Date parse,@Param("beat") String beat);
    //产生订单之后，减少卡内余额
    void subBalance(Traveller traveller);
    //查询旅客的id选定的旅行社
    List<Agency> findAidsByTid(@Param("tid") String tid)throws Exception;
    //根据aid和tid查询出对应的所有的路线id
    List<Route> findRidsByTidAndAid(@Param("tid") String tid,@Param("aid") String aid)throws Exception;
    //根据tid,rid,aid查询出该订单对应的下单时间，下单状态，以及处理订单的时间
    AgencyOrder findAgencyOrder(@Param("aid") String aid,@Param("tid") String tid,@Param("routeId") String routeId) throws Exception;
    //根据tid和aid查询出对应的所有的路线id
    List<Route> findRouteByAidAndTid(@Param("aid") String aid,@Param("tid") String tid) throws Exception;
    //根据旅客的id值查找对应的旅客信息
    Traveller findTravellerById(@Param("tid") String tid) throws Exception;
    //当退订成功之后，增加旅客的余额
    void addBalance(Traveller traveller) ;
    //通过对应的tid aid routeId 实现退订
    boolean deleteAgencyOrder(@Param("tid") String tid, @Param("aid") String aid,@Param("routeId") String routeId);
    //查询所有的航班信息
    List<Flight> findAllFlight() throws Exception;
    //根据航班的编号flightId查询出所对应的座位信息
    Beat findBeatByFid(@Param("flightId")String flightId);
    //查找已经支付的航班订单的信息
    List<FlightOrder> findPayOrder(@Param("parseInt") int parseInt);
    //根据fid查找到对应的flight信息
    Flight findFlightById(@Param("flightId") String flightId) throws Exception;

    List<FlightOrder> findNOPayOrder(@Param("parseInt") int parseInt);
    //    通过航班订单的id值获取具体的订单信息
    FlightOrder findFlightOrderById(@Param("id") int id) throws Exception;
    //更改订单的支付状态，并设置当前的支付时间
    void updateFOrder(@Param("id") String id,@Param("parse") Date parse) throws Exception;
    // 根据id值删除对应的flightOrder订单
    void deleteFlightOrder(@Param("id") String id) throws Exception;
    // 更新 ao的处理状态为“已取消处理”
    void handlerAOrder(@Param("id") Integer id,@Param("status") int status,@Param("time") String time);
}
