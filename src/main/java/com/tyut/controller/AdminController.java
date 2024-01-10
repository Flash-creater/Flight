package com.tyut.controller;

import com.tyut.domain.Admin;
import com.tyut.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/isLogin")
    public ModelAndView isLogin(Admin admin){
        boolean flag = adminService.isLogin(admin);
        System.out.println(flag);
        if (flag){
            return mainJsp();
        }
        else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login/admin");
            modelAndView.addObject("errMsg","登录失败");
            return modelAndView;
        }
    }

    @RequestMapping("/main")
    public ModelAndView mainJsp() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/main");
        return modelAndView;
    }


}
