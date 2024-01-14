package com.tyut.controller;

import com.tyut.domain.Agency;
import com.tyut.domain.Flight;
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
    public String isLogin(HttpServletRequest request, HttpServletResponse response, Agency agency){
        boolean flag = agencyService.isLogin(agency);
        if (flag){
            return mainJsp(agency,request,response);
        }
        else {
            request.setAttribute("errMsg","登录失败");
            return "login/agency";
        }
    }

    @RequestMapping("/main")
    public String mainJsp(Agency agency, HttpServletRequest request,HttpServletResponse response){
        Agency agency1 = agencyMapper.selectPassword(agency);
        request.setAttribute("user",agency1);
        return "agency/main";
    }

    @RequestMapping("/travellerLogin")
    public String toTravellerLogin(){
        return "login/traveller";
    }

    @RequestMapping("/adminLogin")
    public String  toAdminLogin(){
        return "login/admin";
    }
    @RequestMapping("/findAllFlight")
    public String findAllFlight(Model model, HttpServletRequest httpServletRequest){
        List<Flight> flights=agencyService.findAllFlight();
        model.addAttribute("flights", flights);
        return "agency/airline-list";
    }
    @RequestMapping("/depToFinCityFlight")
    public String depToFinCityFlight(String depCity, Model model, HttpServletRequest httpServletRequest){
        List<Flight> flights=agencyService.findFlightByCity(depCity);
        model.addAttribute("flights", flights);
        return "showFlight";
    }
}


