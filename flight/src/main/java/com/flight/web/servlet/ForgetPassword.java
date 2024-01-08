package com.flight.web.servlet;
/*
* 旅客登陆
* */

import com.flight.domain.Agency;
import com.flight.domain.Traveller;
import com.flight.service.Impl.TraServiceImpl;
import com.flight.service.TraService;
import com.flight.utils.MailUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reset/*")
public class ForgetPassword extends BaseServlet {
    private TraService traService = new TraServiceImpl();
    //旅客实现【忘记密码】 功能
    public void forgetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数
        String email = request.getParameter("email");
        //2.调用Service判断邮箱是否正确
        Traveller existUser = null;
        try {
            existUser = traService.findByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(existUser == null ){
            //2.1没有查询到用户，说明邮箱不正确
            //返回错误信息，
            request.setAttribute("err_info", "请确认您的邮箱正确！");
            request.getRequestDispatcher("/pages/forgetPassword.jsp").forward(request,response);
        }else{
            //2.2邮箱正确，向邮箱发送邮件，并提示用户注意查收邮箱
            //3.激活邮件，发送邮件正文
            String content = "<a href='http://8.131.117.230/flight/resetPassword.jsp?id=" + existUser.getId() +"'>点击重置您在【机票预订系统】中设置的密码</a>";
            MailUtils.sendMail(email, content, "【机票预订系统】重置密码邮件");
            request.setAttribute("err_info", "我们已经向您的邮箱发送了【重置密码】邮件，请注意查收！");
            request.getRequestDispatcher("/pages/forgetPassword.jsp").forward(request,response);
        }
    }
    //旅客实现重置密码 功能
    public void resetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数
        String id = request.getParameter("id");
        String resetPassword = request.getParameter("resetPassword");
        //2.调用service进行密码重置操作
        boolean isOk = traService.modifyPassword(id, resetPassword);
        if(isOk){
            //修改成功
            // 2.1跳转到登陆页面
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("<a href='http://8.131.117.230/flight/pages/login/traveller.jsp'>请点击登陆【机票预订系统】</a>");
        }else{
            //2.2 修改失败，返回提示信息
            request.setAttribute("err_info", "修改发生错误，请联系管理员！！");
            request.getRequestDispatcher("/resetPassword.jsp").forward(request, response);
        }
    }
}
