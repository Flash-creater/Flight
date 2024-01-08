package com.flight.web.filter;
/*
* 对旅客是否已经登陆 进行过滤
* */
import com.flight.domain.Agency;
import com.flight.domain.Traveller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class IsLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.获取当前浏览器中的用户
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse)resp;
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        if(uri.contains("traveller")){
            Traveller isLogin = null;
                try {
                    isLogin = (Traveller)request.getSession().getAttribute("user");
                    //2如果用户是否登录
                    if(isLogin == null){
                        //2.1用户未登录 跳转到登陆页面
                        response.sendRedirect(request.getContextPath() + "/pages/login/traveller.jsp");
                    }else{
                        //2.2用户已登录 放行
                        chain.doFilter(req, resp);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    //直接从agency的页面跳转，发生异常,跳转到traveller的登陆页面
                    response.sendRedirect(request.getContextPath() + "/pages/login/traveller.jsp");
                }

        }else if(uri.contains("agency")){
            Agency isLogin = null;
            try {
                isLogin = (Agency) request.getSession().getAttribute("user");
                //2如果用户是否登录
                if(isLogin == null){
                    //2.1用户未登录 跳转到登陆页面
                    response.sendRedirect(request.getContextPath() + "/pages/login/agency.jsp");
                }else{
                    //2.2用户已登录 放行
                    chain.doFilter(req, resp);
                }
            } catch (Exception e) {
                //直接从traveller的页面跳转，发生异常,跳转到agency的登陆页面
                response.sendRedirect(request.getContextPath() + "/pages/login/agency.jsp");
                e.printStackTrace();
            }


        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
