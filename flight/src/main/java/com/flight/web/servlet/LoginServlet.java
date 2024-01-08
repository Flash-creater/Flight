package com.flight.web.servlet;
/*
* 旅客登陆
* */
import com.flight.domain.Admin;
import com.flight.domain.Agency;
import com.flight.domain.Traveller;
import com.flight.service.Impl.TraServiceImpl;
import com.flight.service.TraService;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
@WebServlet("/login/*")
public class LoginServlet extends BaseServlet {
    private TraService traService = new TraServiceImpl();
    //旅客登陆 处理
    public void traveller(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {

        //1.获取用户提交的参数  获取用户登陆的用户名和密码
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String auto_login = request.getParameter("auto_login");
        System.out.println(auto_login);
        //2.调用travellerService来检测用户是否存在
        Traveller existUser = null;
            //通过用户名和密码来验证
        try {
            existUser = traService.findByEmailAndPassword(email, password);

                //2.2用户存在
                //2.2.1. 先销毁session中的User对象
                request.getSession().invalidate();
                //2.2.2 将用户信息存储到服务器中
                request.getSession().setAttribute("user", existUser);
                //2.3如果选择了自动登陆
                //1.是否自动登陆

                if(auto_login != null){
                    //如果选择自动登陆
                    //cookie保存时间为1个小时
                    Cookie cookie = new Cookie("auto",existUser.getEmail() + "-" + existUser.getPassword());
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
                //跳转到旅客页面
                response.sendRedirect(request.getContextPath() + "/pages/traveller/main.jsp");
            
        } catch (Exception e) {
            //存储错误信息 提示用户
            request.setAttribute("errMsg", "邮箱或密码错误！");
            //重新跳转到登陆页面
            request.getRequestDispatcher("/pages/login/traveller.jsp").forward(request,response);
            e.printStackTrace();
        }
    }
    //旅客登陆 处理
    public void agency(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {

        //1.获取用户提交的参数  获取用户登陆的用户名和密码
        String agencyName = request.getParameter("agencyName");
        String password = request.getParameter("password");
        System.out.println(agencyName + "--" + "password");
        // 2.调用travellerService来检测用户是否存在
        Agency existAgency = null;
        // 通过用户名和密码来验证
        try {
            existAgency = traService.findByUserNameAndPassword(agencyName, password);

                //2.1用户存在
                //2.1.1. 先销毁session中的User对象
                request.getSession().invalidate();
                //2.1.2 将用户信息存储到服务器中
                request.getSession().setAttribute("user", existAgency);
                //跳转到旅客页面
                response.sendRedirect(request.getContextPath() + "/pages/agency/main.jsp");

        } catch (Exception e) {
            //存储错误信息 提示用户
            request.setAttribute("errMsg", "用户名或密码错误！");
            //重新跳转到登陆页面
            request.getRequestDispatcher("/pages/login/agency.jsp").forward(request,response);
            e.printStackTrace();
        }
    }
    // 航空公司登陆
    public void admin(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        //1.获取用户提交的参数  获取管理员登陆的用户名和密码
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        System.out.println(name + "--" + password);
        // 2.调用travellerService来检测用户是否存在
        Admin existAdmin = null;
        // 通过用户名和密码来验证
        try {
            existAdmin = traService.findAdmin(name, password);
            System.out.println(existAdmin);
            if(existAdmin != null){
                // 2.1用户存在
                // 2.1.1 销毁之前session中的User对象
                request.getSession().invalidate();

                // 跳转到旅客页面
                response.sendRedirect(request.getContextPath() + "/pages/admin/main.jsp");
            } else {
                // 存储错误信息 提示用户
                request.setAttribute("errMsg", "用户名或密码错误！");
                // 重新跳转到登陆页面
                request.getRequestDispatcher("/pages/login/admin.jsp").forward(request,response);
            }

        } catch (Exception e) {
            // 存储错误信息 提示用户
            request.setAttribute("errMsg", "用户名或密码错误！");
            // 重新跳转到登陆页面
            request.getRequestDispatcher("/pages/login/admin.jsp").forward(request,response);
            e.printStackTrace();
        }
    }
}
