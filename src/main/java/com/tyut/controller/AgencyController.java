package com.tyut.controller;

import com.tyut.domain.Agency;
import com.tyut.domain.Flight;
import com.tyut.domain.FlightOrder;
import com.tyut.mapper.AgencyMapper;
import com.tyut.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
        if (flag) {
            return mainJsp(agency, request, response);
        } else {
            request.setAttribute("errMsg", "登录失败");
            return "login/agency";
        }
    }

    @RequestMapping("/main")
    public String mainJsp(Agency agency, HttpServletRequest request, HttpServletResponse response) {
        Agency agency1 = agencyMapper.selectPassword(agency);
        request.setAttribute("user", agency1);
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

    @RequestMapping("/depToFinCityFlight")
    public String depToFinCityFlight(String depCity, Model model, HttpServletRequest httpServletRequest) {
        List<Flight> flights = agencyService.findFlightByCity(depCity);
        model.addAttribute("flights", flights);
        return "showFlight";
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
}


