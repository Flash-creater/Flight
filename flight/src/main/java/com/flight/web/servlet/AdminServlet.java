package com.flight.web.servlet;

import com.flight.domain.Flight;
import com.flight.domain.FlightOrder;
import com.flight.service.AdminService;
import com.flight.service.Impl.AdminServletImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/*")
public class AdminServlet extends BaseServlet {
    // 创建adminService对象
    private AdminService adminService = new AdminServletImpl();
    // 实现航空公司的注销功能
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.销毁session中的User对象
        request.getSession().invalidate();
        //2.销毁Cookie中的用户
        Cookie cookie = new Cookie("auto", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        //2.跳转到登陆入口页面
        response.sendRedirect(request.getContextPath() + "/pages/login/admin.jsp");
    }
    // 查询所有的航班订单
    // 获取所有航班的信息
    public void findAllFlight(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        List<Flight> flightList = null;
        //1.调用service查找所有的航班信息
        try {
            flightList = adminService.findAllFlight();

        } catch (Exception e) {
            e.printStackTrace();
            //发生错误，跳转到错误页面
            response.sendRedirect("/pages/admin/500.jsp");
        }
        //2.将航班信息返回到airline-list的航班信息
        request.setAttribute("flightList", flightList);
        request.getRequestDispatcher("/pages/admin/airline-list.jsp").forward(request, response);
    }
    // 获取所有航班订单
    public void allFlightOrder(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        //1.
        List<FlightOrder> flightOrders = null;
        // 2.调用service查找已经支付的航班信息
        try {
            flightOrders = adminService.findAllOrder();
            if(flightOrders.size() == 0){
                request.setAttribute("errMsg", "您尚未有任何订单！");
                request.getRequestDispatcher("/pages/admin/payOrder.jsp").forward(request, response);
            }else{
                //3.将航班信息返回到airline-list的航班信息
                request.setAttribute("flightOrderList", flightOrders);
                request.getRequestDispatcher("/pages/admin/payOrder.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //发生错误，跳转到错误页面
            response.sendRedirect("/pages/admin/500.jsp");
        }
    }

    // 获取订单信息

    public String getOrderInfo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        //1.获取航班订单的id
        String id = request.getParameter("id");
        //2.通过航班订单的id值获取具体的订单信息
        FlightOrder fo = null;
        try {
            fo = adminService.findFlightOrderById(id);
            //3.将对应的航班信息传递给ticket-info.jsp页面
            request.setAttribute("flightOrder", fo);
            request.getRequestDispatcher("/pages/admin/order-info.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/pages/admin/500.jsp");
            e.printStackTrace();
        }
    }
}
