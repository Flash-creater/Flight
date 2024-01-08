package com.flight.web.servlet;

import com.flight.domain.Flight;
import com.flight.domain.AgencyOrder;
import com.flight.domain.FlightOrder;
import com.flight.domain.Traveller;
import com.flight.service.AgencyService;
import com.flight.service.Impl.AgencyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/agency/*")
public class AgencyServlet extends BaseServlet {
   private AgencyService  agService= new AgencyServiceImpl();
   //实现旅行社的注销功能
   public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //1.销毁session中的User对象
       request.getSession().invalidate();
       //2.销毁Cookie中的用户
       Cookie cookie = new Cookie("auto", null);
       cookie.setPath("/");
       cookie.setMaxAge(0);
       response.addCookie(cookie);
       //2.跳转到登陆入口页面
       response.sendRedirect(request.getContextPath() + "/pages/login/agency.jsp");
   }
    // 获取所有航班的信息
    public void findAllFlight(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        List<Flight> flightList = null;
        //1.调用service查找所有的航班信息
        try {
            flightList = agService.findAllFlight();
        } catch (Exception e) {
            e.printStackTrace();
            //发生错误，跳转到错误页面
            response.sendRedirect("/pages/agency/500.jsp");
        }
        //2.将航班信息返回到airline-list的航班信息
        request.setAttribute("flightList", flightList);
        request.getRequestDispatcher("/pages/agency/airline-list.jsp").forward(request, response);
    }

    // 查询所有的旅客
    public void findAllTraveller(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
       //1.获取agency的id值
       String id = request.getParameter("id");
       //2.调用service查询所有的旅客
        List<Traveller> travellerList = null;

        try {
            travellerList = agService.findAllTraveller(id);
            // 如果旅客为空，请返回提示信息
            if(travellerList.size() == 0){
                //2.1 存储到request的作用域中，并跳转到traveller-list.jsp
                request.setAttribute("errMsg", "你还没有旅客哦，快快去拓展自己的业务吧！");
                request.getRequestDispatcher("/pages/agency/traveller-list.jsp").forward(request, response);
            }else{
                //2.1 存储到request的作用域中，并跳转到traveller-list.jsp
                request.setAttribute("travellerList", travellerList);
                request.getRequestDispatcher("/pages/agency/traveller-list.jsp").forward(request, response);
            }

        } catch (Exception e) {
            //发生异常 跳转到500.jsp页面
            response.sendRedirect(request.getContextPath() + "/pages/agency/500.jsp");
            e.printStackTrace();
        }

    }
    //旅行社 查询 旅客选择的旅行社的路线 的订单 的详情
    public void orderInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取传过来的tid,rid,aid，获取响应的route订单信息
        int tid = Integer.parseInt(request.getParameter("tid"));
        String aid = request.getParameter("aid");
        String routeId = request.getParameter("rid");
        //2.调用service获取agencyOrder对象
        AgencyOrder ao = null;
        try {
            ao = agService.findAgencyOrderByIds(tid, aid, routeId);
        } catch (Exception e) {
            //发生异常，返回500.jsp
            response.sendRedirect(request.getContextPath()+"/pages/agency/500.jsp");
            e.printStackTrace();
        }
        if(ao == null){
            //发生错误，返回500.jsp
            response.sendRedirect(request.getContextPath()+"/pages/agency/500.jsp");
        }else{
            //向myAgencyOrder-info.jsp页面传回ao对象
            request.setAttribute("ao", ao);
            request.getRequestDispatcher("/pages/agency/order-info.jsp").forward(request,response);
        }
    }
    //查找出发城市是city的当前的航班
    public void findFlight(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        //1.获取city获取当前路线的出发城市,来查找对应的航班
        String tid = request.getParameter("tid");
        String city = request.getParameter("city");
        String rid = request.getParameter("rid");

        List<Flight> flightList = null;
        //2.调用service查找出发城市是city的航班信息
        try {
            flightList = agService.findFlightByCity(city);
        } catch (Exception e) {
            e.printStackTrace();
            //发生错误，跳转到错误页面
            response.sendRedirect("/pages/agency/500.jsp");
        }
        //2.将航班信息返回到airline-list的航班信息
        request.setAttribute("flightList", flightList);
        request.getRequestDispatcher("/pages/agency/airline-list.jsp?tid="+tid + "&&rid=" + rid).forward(request, response);

    }
    //旅行社进行订票时，对具体的票的信息和 旅客信息进行确认
    public void flightDetail(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
       //1.获取fid 和 tid
       String fid = request.getParameter("fid");
       String tid = request.getParameter("tid");
       String rid = request.getParameter("rid");
       //获取舱位等级和价格
        String beat = request.getParameter("beat");
        String price = request.getParameter("price");
       //2.调用service查询到对应的flight 和 traveller
        Flight flight = null;
        try {
            flight = agService.findFlightDetail(fid, tid);
            //3.将查找到的flight传到ticket-info.jsp页面
            request.setAttribute("flight", flight);
            request.getRequestDispatcher("/pages/agency/ticket-info.jsp?beat=" + beat + "&&price="+price + "&&rid="+rid).forward(request,response);
        } catch (Exception e) {
            //发生异常 跳转到500.jsp页面
            response.sendRedirect(request.getContextPath() + "/pages/agency/500.jsp");
            e.printStackTrace();
        }
    }
    //生成flight订单
    public void addFlightOrder(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        //1.获取routeId, aid, tid,flightId,price,beat 的信息
        String aid = request.getParameter("aid");
        String tid = request.getParameter("tid");
        String flightId = request.getParameter("fid");
        String routeId = request.getParameter("rid");
        String beat = request.getParameter("beat");
        double price = Double.parseDouble(request.getParameter("price"));
       //2.根据对应的routeId，aid, tid,fid查找唯一的订单
        FlightOrder fo = agService.findFOrderByIds(aid, routeId, tid, flightId);
        if(fo != null){
            //3.1如果已经产生了对应的订单，则提示错误信息，
            request.setAttribute("errMsg", "您已经选定过，不可以重复选定！！");
            request.getRequestDispatcher("/pages/agency/ticket-info.jsp").forward(request, response);
        }else{
            //3.2 没有产生过订单，则生成新的订单
            boolean isOk = false;
            try {
                    isOk = agService.addFOrder(aid, routeId, tid, flightId, beat, price);
                    if(isOk){
                        //订单生成成功，返回 [我的旅行社] 界面
                        request.setAttribute("errMsg", "产生订单成功,请到【订单管理】--【我的订单】进行查看！！");
                        request.getRequestDispatcher("/pages/agency/ticket-info.jsp").forward(request, response);
                    }else{
                        request.setAttribute("errMsg", "订单添加失败，请联系管理员！！");
                        request.getRequestDispatcher("/pages/agency/ticket-info.jsp").forward(request, response);
                    }

            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/pages/agency/500.jsp");
            }

        }

    }
    public void findFOrder(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
       //1.获取aid,通过aid 查找flightOrder 获取该旅行社对应的相关的航班信息
        String aid = request.getParameter("aid");
        //2.调用service 查找到相关的traveller flight agencyOrder的信息
        List<FlightOrder> flightOrderList = null;
        try {
            flightOrderList = agService.findFOrder(aid);
            if(flightOrderList.size() == 0){
                request.setAttribute("errMsg", "您尚未任何航班订单！！");
                request.getRequestDispatcher("/pages/agency/flightOrder-list.jsp").forward(request, response);
            }else{
                request.setAttribute("flightOrderList", flightOrderList);
                request.getRequestDispatcher("/pages/agency/flightOrder-list.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errMsg", "您尚未任何航班订单！！");
            request.getRequestDispatcher("/pages/agency/flightOrder-list.jsp").forward(request, response);

        }

    }
}
