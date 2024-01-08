package com.flight.dao.Impl;

import com.flight.dao.TraDao;
import com.flight.domain.*;
import com.flight.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class TraDaoImpl implements TraDao {
    //调用spring提供的jdbc处理数据库
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    //通过邮箱和密码进行查找用户
    @Override
    public Traveller findByEmailAndPassword(String email, String password) throws Exception{
        //定义sql
        String sql = "select * from traveller where email = ? and password = ?";
        Traveller traveller = null;
            //查询user
        traveller = template.queryForObject(sql, new BeanPropertyRowMapper<Traveller>(Traveller.class),email, password);
        return traveller;
    }

    //通过证号和密码进行查找旅行社
    @Override
    public Agency findByUserNameAndPassword(String agencyName, String password) throws Exception{
        //定义sql
        String sql = "select * from agency where agencyName = ? and password = ?";
        Agency agency = null;
            //查询user
            agency = template.queryForObject(sql, new BeanPropertyRowMapper<Agency>(Agency.class),agencyName, password);
            return agency;
    }
    //注册用户
    @Override
    public void registerUser(Traveller regUser) {
        //定义Sql
        String sql = "insert into traveller(userName,trueName, password,email, phone, ID_Card) values(?, ?, ?, ?, ?, ?)";
        try {
            //查询Sql
           template.update(sql, regUser.getUserName(), regUser.getTrueName(), regUser.getPassword(), regUser.getEmail(), regUser.getPhone(), regUser.getID_Card());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    //通过用户名查找
    @Override
    public Traveller findByUserName(String userName) throws Exception {
        //定义sql
        String sql = "select * from traveller where username = ?";
        Traveller traveller = null;
        try {
            //查询user
            traveller = template.queryForObject(sql, new BeanPropertyRowMapper<Traveller>(Traveller.class),userName);
            return traveller;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    //通过邮箱查找
    @Override
    public Traveller findByEmail(String email) throws Exception {
        //定义sql
        String sql = "select * from traveller where email = ?";
        Traveller traveller = null;
        try {
            //查询user
            traveller = template.queryForObject(sql, new BeanPropertyRowMapper<Traveller>(Traveller.class),email);
            return traveller;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }

    }
    //通过用户id和密码查找用户
    @Override
    public Traveller checkPassword(String id, String password) {

        //定义sql
        String sql = "select * from traveller where id = ? and password = ?";
        Traveller traveller = null;
        try {
            //查询user
            traveller = template.queryForObject(sql, new BeanPropertyRowMapper<Traveller>(Traveller.class),id, password);
            return traveller;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    //修改心得密码
    @Override
    public boolean modifyPassword(String id, String newPassword) {
        //1.定义Sql
        String sql = "update traveller set password = ? where id = ?";
        //2.执行sql
        try {
            template.update(sql, newPassword, id);
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean save(Traveller saveUser) {
        //1.定义sql
        String sql = "update traveller set userName = ?, trueName = ?, phone = ?, sex = ?, ID_Card = ?, birthday = ?, balance = ? where id = ?";
        try {
            template.update(sql, saveUser.getUserName(), saveUser.getTrueName(), saveUser.getPhone(), saveUser.getSex(), saveUser.getID_Card(), saveUser.getBirthday(), saveUser.getBalance(), saveUser.getId());
            return true;
        } catch (DataAccessException | ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
    //查找所有的旅行社
    @Override
    public List<Agency> findAllAgency() {
        String sql = "select * from agency";
        List<Agency> agencies = null;
        try {
            agencies = template.query(sql,new BeanPropertyRowMapper<Agency>(Agency.class));
            return agencies;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    //根据id值查找旅行社
    @Override
    public Agency findAgencyById(String id) throws Exception{
        String sql = "select * from agency where id = ?";
        Agency agency = null;
        agency = template.queryForObject(sql, new BeanPropertyRowMapper<Agency>(Agency.class), id);
        return agency;

    }
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
    //调用Service查找身份证号是否已经与有人使用
    @Override
    public Traveller findTraveller(String trueName, String phone, String sex, String id_card) {
        String sql = "select * from traveller where trueName = ? and phone = ? and sex = ? and ID_Card = ?";
        Traveller traveller = null;
        traveller = template.queryForObject(sql, new BeanPropertyRowMapper<Traveller>(Traveller.class), trueName, phone,sex, id_card);
        return traveller;
    }
    //根据对应的routeId，aid, tid查找唯一的订单
    @Override
    public AgencyOrder findOrderById(String aid, String routeId, Integer tid) {
        String sql = "select * from agency_order where aid=? and routeId = ? and tid= ?";
        AgencyOrder ao = null;
        try {
            ao = template.queryForObject(sql, new BeanPropertyRowMapper<AgencyOrder>(AgencyOrder.class), aid, routeId, tid);
            return ao;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    //根据对应的routeId，aid, tid产生唯一的订单,已经下单的时间
    @Override
    public boolean addOrder(String aid, String routeId, Integer tid, Date orderTime, String beat) {
        String sql = "insert into agency_order(aid, routeId, tid, orderTime, beat) values(?, ?, ?, ?, ?)";
        int count = template.update(sql, aid, routeId, tid, orderTime, beat);
        if(count > 0)
            return true;
        else
            return false;
    }
    //产生订单之后，减少卡内余额
    @Override
    public void subBalance(Traveller traveller, double price) {
        double newBalance = traveller.getBalance() - price;
        String sql = "update traveller set balance = ? where id = ?";
        template.update(sql, newBalance, traveller.getId());
    }
    //查询出旅客选取的旅行社的id
    @Override
    public List<Agency> findAidsByTid(String tid) throws Exception{
        String sql = "SELECT * FROM agency WHERE id IN (SELECT aid FROM agency_order WHERE id IN (SELECT MIN(id) FROM agency_order WHERE tid = ? GROUP BY aid ))";
        List<Agency> aids = template.query(sql, new BeanPropertyRowMapper<Agency>(Agency.class), tid);
        return aids;
    }
    //根据aid和tid查询出对应的所有的路线id
    @Override
    public List<Route> findRidsByTidAndAid(String tid, String aid) throws Exception{
        String sql = "select * from route where routeID in(SELECT routeId FROM agency_order WHERE tid = ? and aid = ?)";
        List<Route> Routes = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), tid, aid);
        return Routes;
    }
    //根据tid,rid,aid查询出该订单对应的下单时间，下单状态，以及处理订单的时间
    @Override
    public AgencyOrder findAgencyOrder(String aid, String tid, String rid) throws Exception{
        String sql = "select * from agency_order where aid=? and tid =? and routeId=?";
        AgencyOrder ao = null;
        ao = template.queryForObject(sql, new BeanPropertyRowMapper<AgencyOrder>(AgencyOrder.class),aid,tid,rid);
        return ao;
    }
    //根据tid和aid查询出对应的所有的路线id

    @Override
    public List<Route> findRouteByAidAndTid(String aid, String tid) throws Exception{
        String sql = "select * from route where routeID in (select routeId from agency_order where aid = ? and tid = ?)";
        List<Route> route =null;
        route = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), aid, tid);
        return route;
    }
    //根据旅客的id值查找对应的旅客信息
    @Override
    public Traveller findTravellerById(String tid) throws Exception{
        String sql = "select * from traveller where id = ?";
        Traveller traveller = null;
        traveller = template.queryForObject(sql, new BeanPropertyRowMapper<Traveller>(Traveller.class), tid);
        return traveller;
    }
    //当退订成功之后，增加旅客的余额
    @Override
    public void addBalance(Traveller traveller, double price) throws Exception {
        double newBalance = traveller.getBalance() + price;
        String sql = "update traveller set balance = ? where id = ?";
        template.update(sql, newBalance, traveller.getId());
    }
    //通过对应的tid aid routeId 实现退订
    @Override
    public boolean deleteAgencyOrder(String tid, String aid, String routeId) {
        String sql = "delete from agency_order where tid = ? and aid = ? and routeId = ?";
        int count = template.update(sql, tid, aid, routeId);
        if(count > 0){
            return true;
        }else{
            return false;
        }

    }
    //查询所有的航班信息
    @Override
    public List<Flight> findAllFlight() throws Exception{
        String sql  = "select * from airline";
        List<Flight> flights = null;
        flights = template.query(sql, new BeanPropertyRowMapper<Flight>(Flight.class));

        return flights;
    }
    //根据航班的编号flightId查询出所对应的座位信息
    @Override
    public Beat findBeatByFid(String flightId) {
      Beat beat = null;
      String sql = "select * from beat where flightId = ?";
      beat = template.queryForObject(sql, new BeanPropertyRowMapper<Beat>(Beat.class), flightId);
      return beat;
    }
    //查找已经支付的航班订单的信息
    @Override
    public List<FlightOrder> findPayOrder(int tid) {
        List<FlightOrder> flightOrders = null;
        String sql = "select * from flight_order where tid=? and payStatus = 1";
        try {
            flightOrders = template.query(sql, new BeanPropertyRowMapper<FlightOrder>(FlightOrder.class), tid);
            //由已经支付的订单，则返回
            return flightOrders;
        } catch (Exception e) {
            e.printStackTrace();
            //没有支付的订单，则返回null
            return null;
        }

    }
    //根据fid查找到对应的flight信息
    @Override
    public Flight findFlightById(String flightId) throws Exception{
        Flight flight = null;
        String sql = "select * from airline where flightId = ?";
        flight = template.queryForObject(sql, new BeanPropertyRowMapper<Flight>(Flight.class), flightId);

        return flight;
    }
    //查找未支付的航班订单的信息
    @Override
    public List<FlightOrder> findNOPayOrder(int tid) {
        List<FlightOrder> flightOrders = null;
        String sql = "select * from flight_order where tid=? and payStatus = 0";
        try {
            flightOrders = template.query(sql, new BeanPropertyRowMapper<FlightOrder>(FlightOrder.class), tid);
            //由已经支付的订单，则返回
            return flightOrders;
        } catch (Exception e) {
            e.printStackTrace();
            //没有支付的订单，则返回null
            return null;
        }

    }
    //    通过航班订单的id值获取具体的订单信息
    @Override
    public FlightOrder findFlightOrderById(int id) throws Exception{
        FlightOrder fo = null;
        String sql = "select * from flight_order where id = ?";
        fo = template.queryForObject(sql, new BeanPropertyRowMapper<FlightOrder>(FlightOrder.class), id);
        return fo;
    }
    //更改订单的支付状态，并设置当前的支付时间
    @Override
    public void updateFOrder(String id, Date nowTime) throws Exception{
      String sql = "update flight_order set payStatus = 1,payTime = ? where id = ?";
      template.update(sql, nowTime, id);
    }
    // 根据id值删除对应的flightOrder订单
    @Override
    public void deleteFlightOrder(String id) throws Exception {
       String sql = "delete from flight_order where id = ?";
       template.update(sql, id);
    }
    // 在取消航班订单之后，更新旅行社订单中的处理状态
    @Override
    public void handlerAOrder(Integer id, int status, String time) {
        String sql = "update agency_order set handlerTime = ?, orderStatus = ? where id = ?";
        template.update(sql,time, status, id);
    }

    @Override
    public Admin findAdmin(String name, String password) throws Exception {
        Admin admin = null;
        String sql = "select * from admin where name = ? and password = ?";
        try {
            admin = template.queryForObject(sql, new BeanPropertyRowMapper<Admin>(Admin.class), name, password);
        } catch (DataAccessException e) {
            e.printStackTrace();
            admin = null;
        }
        return admin;
    }
}
