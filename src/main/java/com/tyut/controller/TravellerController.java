package com.tyut.controller;

import com.sun.deploy.net.HttpResponse;
import com.tyut.domain.Traveller;
import com.tyut.mapper.TravellerMapper;
import com.tyut.service.TravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;

@Controller
@RequestMapping("/travellerController")
public class TravellerController {
    @Autowired
    private TravellerService travellerService;
    @Autowired
    private TravellerMapper travellerMapper;

    @RequestMapping("/isLogin")
    public String isLogin(HttpServletRequest request, HttpServletResponse response, Traveller traveller){
        boolean flag = travellerService.isLogin(traveller);
        System.out.println(flag);
        String auto_login = request.getParameter("auto_login");
        if (flag){
            if(auto_login != null){
                //1.是否自动登陆
                //如果选择自动登陆
                //cookie保存时间为1个小时
                Cookie cookie = new Cookie("auto",traveller.getEmail() + "-" + traveller.getPassword());
                cookie.setMaxAge(60*60*24);
                cookie.setPath("/");
                response.addCookie(cookie);
            }else{
                //没有选择自动登陆 则关闭浏览器就销毁
                Cookie cookie = new Cookie("auto",null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            return mainJsp(traveller,request,response);
        }
        else {
            request.setAttribute("errMsg","登录失败");
            return "login/traveller";
        }
    }

    @RequestMapping("/main")
    public String mainJsp(Traveller traveller, HttpServletRequest request,HttpServletResponse response){
        Traveller traveller1 = travellerMapper.selectPassword(traveller);
        //用户存在
        //先销毁session中的User对象
        request.getSession().invalidate();
        //将用户信息存储到服务器中
        request.getSession().setAttribute("user", traveller1);

        return "traveller/main";
    }

    @RequestMapping("/agencyLogin")
    public String toAgencyLogin(){
        return "login/agency";
    }

    @RequestMapping("/adminLogin")
    public String  toAdminLogin(){
        return "login/admin";
    }

    @RequestMapping("/toForgetPassword")
    public String  toForgetPassword(){
        return "forgetPassword";
    }

    @RequestMapping("/toResetPassword")
    public String toResetPassword(){
        return "traveller/password";
    }

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register/register";
    }

    @RequestMapping("/register")
    public String addTraveller(Traveller traveller,HttpServletRequest request,HttpServletResponse response){
        int i = travellerService.addTraveller(traveller);
        if (i != 0) return mainJsp(traveller,request,response);
        else {
            return "register/register";
        }
    }

    @RequestMapping("/logout")
    public String  logout(HttpServletRequest request, HttpServletResponse response) {
        //1.销毁session中的User对象
        request.getSession().invalidate();
        //2.销毁Cookie中的用户
        Cookie cookie = new Cookie("auto", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        //2.跳转到登陆入口页面
        return "login/traveller";
    }

    @RequestMapping("/modifyPassword")
    public String modifyPassword(HttpServletRequest request, HttpServletResponse response){
        //1.获取参数
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String newPassword =request.getParameter("newPassword");
        //2.调用Service查找用户 通过用户id和password查找用户
        Traveller modfiyTra = travellerService.checkPassword(id, password);
        //2.1判断是否有这个用户
        if(modfiyTra == null ){
            //用户不存在，证明密码错误 返回提示信息
            request.setAttribute("err_info", "请确认您的密码正确！！");
            return "";
        }else{
            //用户存在，则修改密码，
            boolean isOk = travellerService.modifyPassword(id, newPassword);
            if(isOk){
                //修改成功
                //1.清除session和cookie中的信息
                System.out.println("修改成功");
                //1.1销毁Cookie中的用户
                Cookie cookie = new Cookie("auto", null);
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                // 2.跳转到登录页面
                return "login/traveller";
            }else{
                //修改失败，返回提示信息
                request.setAttribute("err_info", "修改发生错误，请联系管理员！！");
                return "traveller/password";
            }

        }

    }
}
