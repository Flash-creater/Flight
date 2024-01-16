package com.tyut.controller;

import com.tyut.domain.Agency;
import com.tyut.domain.Flight;
import com.tyut.domain.FlightOrder;
import com.tyut.domain.Traveller;
import com.tyut.mapper.AgencyMapper;
import com.tyut.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/agencyController")
public class AgencyController {
    @Autowired
    private AgencyService agencyService;
    @Autowired
    private AgencyMapper agencyMapper;

    @RequestMapping("/isLogin")
    public String isLogin(HttpServletRequest request, HttpServletResponse response, Agency agency) {
        boolean flag = agencyService.isLogin(agency);
        System.out.println(flag);
        String auto_login = request.getParameter("auto_login");
        if (flag) {
            if (auto_login != null) {
                //1.是否自动登陆
                //如果选择自动登陆
                //cookie保存时间为1个小时
                Cookie cookie = new Cookie("auto", agency.getAgencyName() + "-" + agency.getPassword());
                cookie.setMaxAge(60 * 60 * 24);
                cookie.setPath("/");
                response.addCookie(cookie);
            } else {
                //没有选择自动登陆 则关闭浏览器就销毁
                Cookie cookie = new Cookie("auto", null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            Agency agency1 = agencyMapper.selectPassword(agency);
            //用户存在
            //先销毁session中的User对象
            request.getSession().invalidate();
            //将用户信息存储到服务器中
            request.getSession().setAttribute("user", agency1);
            return "agency/main";
        } else {
            request.setAttribute("errMsg", "登录失败");
            return "login/agency";
        }
    }

    @RequestMapping("/main")
    public String mainJsp(HttpServletRequest request) {
        request.getSession();
        return "agency/main";
    }

    @RequestMapping("/travellerLogin")
    public String toTravellerLogin() {
        return "login/traveller";
    }

    @RequestMapping("/adminLogin")
    public String toAdminLogin() {
        return "login/admin";
    }

    @RequestMapping("/findAllFlight")
    public String findAllFlight(Model model, HttpServletRequest httpServletRequest) {
        List<Flight> flights = agencyService.findAllFlight();
        model.addAttribute("flights", flights);
        return "agency/airline-list";
    }

    @RequestMapping("/flightDetail")
    public String flightDetail(String fid, String tid, Model model, HttpServletRequest httpServletRequest) {
        Flight flight = agencyService.findFlightDetail(fid, tid);
        model.addAttribute("flight", flight);
        return "agency/ticket-info";
    }

    @RequestMapping("/addFlightOrder")
    public String addFlightOrder(String aid, String tid, String fid, String routeId, String flightId, String beat, double price, Model model, HttpServletRequest httpServletRequest) {
        FlightOrder fo = agencyService.findFOrderByIds(aid, routeId, tid, flightId);
        if (fo != null) {
            //3.1如果已经产生了对应的订单，则提示错误信息，
            model.addAttribute("errMsg", "您已经选定过，不可以重复选定！！");
            return ("agency/ticket-info");
        } else {
            //3.2 没有产生过订单，则生成新的订单
            boolean isOk = false;
            isOk = agencyService.addFOrder(aid, routeId, tid, flightId, beat, price);
            if (isOk) {
                //订单生成成功，返回 [我的旅行社] 界面
                model.addAttribute("errMsg", "产生订单成功,请到【订单管理】--【我的订单】进行查看！！");
                return ("agency/ticket-info");
            } else {
                model.addAttribute("errMsg", "订单添加失败，请联系管理员！！");
                return ("agency/ticket-info");
            }

        }
    }

    @RequestMapping("findFOrder")
    public String findFOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.获取aid,通过aid 查找flightOrder 获取该旅行社对应的相关的航班信息
        String aid = request.getParameter("aid");
        System.out.println(aid);
        //2.调用service 查找到相关的traveller flight agencyOrder的信息
        List<FlightOrder> flightOrderList = null;
        try {
            flightOrderList = agencyService.findFOrder(aid);
            if (flightOrderList.size() == 0) {
                request.setAttribute("errMsg", "您尚未任何航班订单！！");
                return ("/agency/flightOrder-list");
            } else {
                request.setAttribute("flightOrderList", flightOrderList);
                return ("/agency/flightOrder-list");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errMsg", "您尚未任何航班订单！！！");
            return ("/agency/flightOrder-list");
        }
    }

    @RequestMapping("/findAllTraveller")
    public String findAllTraveller(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.获取agency的id值
        String id = request.getParameter("id");
//        System.out.println(id);
        //2.调用service查询所有的旅客
        List<Traveller> travellerList = null;

        try {
            travellerList = agencyService.findAllTraveller(id);
            System.out.println(travellerList);
            // 如果旅客为空，请返回提示信息
            if (travellerList.size() == 0) {
                //2.1 存储到request的作用域中，并跳转到traveller-list.jsp
                request.setAttribute("errMsg", "你还没有旅客哦，快快去拓展自己的业务吧！");
                return ("/agency/traveller-list");
            } else {
                //2.1 存储到request的作用域中，并跳转到traveller-list.jsp
                request.setAttribute("travellerList", travellerList);
                return ("/agency/traveller-list");
            }

        } catch (Exception e) {
            //发生异常 跳转到500.jsp页面
            return "agency/500";
        }

    }
    @RequestMapping("/agency-info")
    public String agencyInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.获取agency的id值
        String id = request.getParameter("id");
        //2.调用service查询所有的旅客
        Agency user = null;
        try {
            user = agencyService.findAgencyById(id);
            // 如果旅客为空，请返回提示信息
            if (user == null) {
                //2.1 存储到request的作用域中，并跳转到agency-info.jsp
                request.setAttribute("errMsg", "旅行社为空");
                return ("/agency/agency-info");
            } else {
                //2.1 存储到request的作用域中，并跳转到agenct-info.jsp
                request.setAttribute("user", user);
                return ("/agency/agency-info");
            }

        } catch (Exception e) {
            //发生异常 跳转到500.jsp页面
            return "agency/500";
        }
    }
    @RequestMapping("/findFlight")
    public String findFlight(HttpServletRequest request, HttpServletResponse response)  throws Exception {
        //1.获取city获取当前路线的出发城市,来查找对应的航班
        String tid = request.getParameter("tid");
        String city = request.getParameter("city");
        String rid = request.getParameter("rid");

        List<Flight> flights = null;
        //2.调用service查找出发城市是city的航班信息
        try {
            flights = agencyService.findFlightByCity(city);
        } catch (Exception e) {
            e.printStackTrace();
            //发生错误，跳转到错误页面
            return("/agency/500");
        }
        //2.将航班信息返回到airline-list的航班信息
        request.setAttribute("flights", flights);
        return ("/agency/airline-list");
    }

}


