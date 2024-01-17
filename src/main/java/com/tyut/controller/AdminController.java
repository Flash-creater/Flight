package com.tyut.controller;

import com.tyut.domain.*;
import com.tyut.mapper.AdminMapper;
import com.tyut.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/adminController")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminMapper adminMapper;

    @RequestMapping("/isLogin")
    public String isLogin(HttpServletRequest request, HttpServletResponse response, Admin admin){
        boolean flag = adminService.isLogin(admin);
        if (flag){
            return mainJsp(admin,request,response);
        }
        else {
            request.setAttribute("errMsg","登录失败");
            return "/login/admin";
        }
    }

    @RequestMapping("/main")
    public String mainJsp(Admin admin, HttpServletRequest request,HttpServletResponse response){
        Admin admin1 = adminMapper.selectPassword(admin);
        request.setAttribute("user",admin1);
        return "/admin/main";
    }

    @RequestMapping("/travellerLogin")
    public String toTravellerLogin(){
        return "/login/traveller";
    }

    @RequestMapping("/agencyLogin")
    public String  toAgencyLogin(){
        return "/login/agency";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        //1.销毁session中的User对象
        request.getSession().invalidate();
        //2.销毁Cookie中的用户
        Cookie cookie = new Cookie("auto", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        //2.跳转到登陆入口页面
        return "/login/admin";
    }
    //航空公司查询所有航班
    @RequestMapping("/findAllFlight")
    public String findAllFlight(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        List<Flight> flightList = null;
        //1.调用service查找所有的航班信息
        try {
            flightList = adminService.findAllFlight();

        } catch (Exception e) {
            e.printStackTrace();
            //发生错误，跳转到错误页面
            return("/admin/500");
        }
        //2.将航班信息返回到airline-list的航班信息
        request.setAttribute("flightList", flightList);
        return("/admin/airline-list");
    }
    //航空公司查询所有订单
    @RequestMapping("/findAllOrder")
    public String findAllOrder(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        List<FlightOrder> flightOrders = null;
        // 1.调用service查找已经支付的航班信息
        try {
            flightOrders = adminService.findAllOrder();
            if(flightOrders.size() == 0){
                request.setAttribute("errMsg", "您尚未有任何订单！");
                return("/admin/payOrder");
            }else{
                System.out.println(flightOrders.size());
                //2.将航班信息返回到airline-list的航班信息
                request.setAttribute("flightOrderList", flightOrders);
                return("/admin/payOrder");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //发生错误，跳转到错误页面
            return("/admin/500");
        }
    }
    //根据航班号查询航班订单情况
    @RequestMapping("/getOrderInfo")
    public String getOrderInfo(HttpServletRequest request,HttpServletResponse response)throws Exception{
        //1.获取订单的id值
        String id = request.getParameter("id");
        //2.根据订单的id获取订单对象
        FlightOrder fo = null;
        try {
            fo = adminService.findFlightOrderById(id);
            //3.将对应的航班信息传递给order-info.jsp页面
            request.setAttribute("flightOrder", fo);
            return"/admin/order-info";
        } catch (Exception e) {
            e.printStackTrace();
            return"/admin/500";

        }
    }
}
