package com.flight.web.servlet;
//利用反射实现对servelet的简单封装，避免设置太多的servlet造成资源浪费
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //完成方法分发
        //1.获取请求路径
        String uri = req.getRequestURI();
//        System.out.println("uri:" + uri);
        //2.获取方法名
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
//        System.out.println("方法名：" + methodName);
        //3.获取方法对象method
        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
            //4.执行方法
            try {
                method.invoke(this, req, resp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                if(uri.contains("traveller")){
                    resp.sendRedirect(req.getContextPath()+ "/pages/traveller/500.jsp");
                }else if(uri.contains("agency")){
                    resp.sendRedirect(req.getContextPath()+ "/pages/agency/500.jsp");
                }
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            if(uri.contains("traveller")){
                resp.sendRedirect(req.getContextPath()+ "/pages/traveller/404.jsp");
            }else if(uri.contains("agency")){
                resp.sendRedirect(req.getContextPath()+ "/pages/agency/404.jsp");
            }
            e.printStackTrace();
        }
    }
}
