package com.tyut.controller;

import com.tyut.domain.Traveller;
import com.tyut.mapper.TravellerMapper;
import com.tyut.service.TravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/traveller")
public class TravellerController {
    @Autowired
    private TravellerService travellerService;
    @Autowired
    private TravellerMapper travellerMapper;

    @RequestMapping("/isLogin")
    public ModelAndView isLogin(Traveller traveller){
        boolean flag = travellerService.isLogin(traveller);
        if (flag){
            return mainJsp(traveller);
        }
        else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login/traveller");
            modelAndView.addObject("errMsg","登录失败");
            return modelAndView;
        }
    }

    @RequestMapping("/main")
    public ModelAndView mainJsp(Traveller traveller) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("traveller/main");
        Traveller traveller1 = travellerMapper.selectPassword(traveller.getPassword());
        modelAndView.addObject("user",traveller1);
        return modelAndView;
    }

    @RequestMapping("/agencyLogin")
    public ModelAndView toTravellerLogin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/agency");
        return modelAndView;
    }

    @RequestMapping("/adminLogin")
    public ModelAndView toAgencyLogin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/admin");
        return modelAndView;
    }

    @RequestMapping("/toForgetPassword")
    public ModelAndView toForgetPassword(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forgetPassword");
        return modelAndView;
    }

    @RequestMapping("/toResetPassword")
    public ModelAndView toResetPassword(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("resetPassword");
        return modelAndView;
    }

    @RequestMapping("/toRegister")
    public ModelAndView toRegister(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register/register");
        return modelAndView;
    }

    @RequestMapping("/register")
    public ModelAndView addTraveller(Traveller traveller){
        int i = travellerService.addTraveller(traveller);
        System.out.println(i);
        if (i != 0) return mainJsp(traveller);
        else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("register/register");
            return modelAndView;
        }
    }
}
