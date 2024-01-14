package com.tyut.controller;

import com.tyut.domain.Admin;
import com.tyut.domain.Agency;
import com.tyut.domain.Flight;
import com.tyut.mapper.AgencyMapper;
import com.tyut.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/agency")
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
        return "admin/main";
    }

    @RequestMapping("/travellerLogin")
    public String toTravellerLogin(){
        return "login/traveller";
    }

    @RequestMapping("/adminLogin")
    public String  toAdminLogin(){
        return "login/admin";
    }

}
