package com.flight.web.filter;
/*
* 实现自动登陆
* */
import com.flight.domain.Traveller;
import com.flight.service.Impl.TraServiceImpl;
import com.flight.service.TraService;

import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutoLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        //1.判断浏览器中用户是否存在
        Traveller existUser = null;
        try {
            existUser = (Traveller)request.getSession().getAttribute("user");
            if(existUser != null){
                //1.1用户存在，就直接放行
                chain.doFilter(req,resp);
            }else{
                System.out.println("调用了自动登陆！！！！");
                //1.2用户不存在，判断cookie中是否有存储
                //2.获取所有的cookie
                Cookie [] cookies = request.getCookies();

                for(Cookie cookie:cookies){
                    //2.1遍历所有的cookie，查找是否有“auto”
                    if("auto".equals(cookie.getName())){
                        System.out.println("调用了自动登陆！！！！---查询到了用户");
                        //2.2 有存储的cookie信息，获取用户名和密码
                        String user = cookie.getValue();
                        String []str = user.split("-");
                        String email = str[0];
                        String password = str[1];
                        System.out.println(user);
                        System.out.println(email+password);

                        //2.3调用service判断用户是否更改过密码
                        TraService traService = new TraServiceImpl();
                        Traveller traveller = null;
                        try {
                            traveller = traService.findByEmailAndPassword(email, password);
                        } catch (Exception e) {
                            //用户修改过密码，则重新跳转到登陆页面
                            request.setAttribute("errMsg","发生错误，请联系管理员！！");
                            //重新跳转到登陆界面
                            request.getRequestDispatcher("/pages/login/traveller.jsp").forward(request,response);

                            e.printStackTrace();
                        }
                        if(traveller == null){
                            //用户修改过密码，则重新跳转到登陆页面
                            request.setAttribute("errMsg","您的密码已被修改过，请重新登录！！");
                            //重新跳转到登陆界面
                            request.getRequestDispatcher("/pages/login/traveller.jsp").forward(request,response);
                        }else{
                            //用户被存在cookie中 则存储到session
                            request.getSession().setAttribute("user", traveller);
                            System.out.println(traveller + "1");
                        }
                    }
                }
                //cookie中也没有存储用户信息，放行
                chain.doFilter(req,resp);
            }

        } catch (Exception e) {
            //如果从agency跳转，发生异常，直接返回旅客登陆页面
            response.sendRedirect(request.getContextPath() + "/pages/login/traveller.jsp");
            e.printStackTrace();
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
