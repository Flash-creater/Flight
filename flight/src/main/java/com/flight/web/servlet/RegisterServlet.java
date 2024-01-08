package com.flight.web.servlet;
/*
* 注册
* */
import com.flight.domain.Traveller;
import com.flight.service.Impl.TraServiceImpl;
import com.flight.service.TraService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/register/*")
public class RegisterServlet extends BaseServlet{
    private TraService traService = new TraServiceImpl();
    //旅客注册功能
    public void traveller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数
        Map<String, String[]> map = request.getParameterMap();
        Traveller regUser = new Traveller();
        //2.使用BeanUtils工具进行封装
        try {
            BeanUtils.populate(regUser, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            //3.调用Service进行注册
            boolean isOk =  traService.registerUser(regUser);
            //3.1判断是否注册成功
            if(isOk){
                //注册成功，返回登陆页面
                response.sendRedirect(request.getContextPath() + "/pages/login/traveller.jsp");
            }else{
                //注册失败，提示错误信息，重新注册
                request.setAttribute("err_info", "该邮箱已存在，请重新注册！！！");
                request.getRequestDispatcher("/pages/register/register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
