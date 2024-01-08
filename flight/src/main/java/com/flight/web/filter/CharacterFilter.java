package com.flight.web.filter;
/*
* 过滤编码  防止乱码
* */
import javax.servlet.Filter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CharacterFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws javax.servlet.ServletException, IOException {
        System.out.println("调用了编码过滤器");
        //将父接口转为子接口
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        //获取请求的方法
        String method = request.getMethod();
        //解决请求中文数据乱码问题
        if(method.equalsIgnoreCase("post") || method.equalsIgnoreCase("get")){
            req.setCharacterEncoding("utf-8");
        }
        //处理响应乱码
        response.setContentType("text/html;charset=utf-8");
        chain.doFilter(req, resp);
    }

    public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {

    }

}
