package com.tyut.controller;

import com.tyut.domain.Admin;
import com.tyut.mapper.AdminMapper;
import com.tyut.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
            return "login/admin";
        }
    }

    @RequestMapping("/main")
    public String mainJsp(Admin admin, HttpServletRequest request,HttpServletResponse response){
        Admin admin1 = adminMapper.selectPassword(admin);
        request.setAttribute("user",admin1);
        return "admin/main";
    }

    @RequestMapping("/travellerLogin")
    public String toTravellerLogin(){
        return "login/traveller";
    }

    @RequestMapping("/agencyLogin")
    public String  toAgencyLogin(){
        return "login/agency";
    }

}
