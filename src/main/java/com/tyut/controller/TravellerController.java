package com.tyut.controller;

//import com.sun.deploy.net.HttpResponse;
import com.tyut.domain.*;
import com.tyut.mapper.TravellerMapper;
import com.tyut.service.TravellerService;
import com.tyut.utils.MailUtils;
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
import java.util.List;

@Controller
@RequestMapping("/travellerController")
public class TravellerController {
    @Autowired
    private TravellerService travellerService;
    @Autowired
    private TravellerMapper travellerMapper;

    @RequestMapping("/isLogin")
    public String isLogin(HttpServletRequest request, HttpServletResponse response, Traveller traveller) {
        boolean flag = travellerService.isLogin(traveller);
        String auto_login = request.getParameter("auto_login");
        if (flag) {
            if (auto_login != null) {
                //1.是否自动登陆
                //如果选择自动登陆
                //cookie保存时间为1个小时
                Cookie cookie = new Cookie("auto", traveller.getEmail() + "-" + traveller.getPassword());
                cookie.setMaxAge(60 * 60 * 24);
                cookie.setPath("/");
                response.addCookie(cookie);
            } else {
                //没有自动登陆 则关闭浏览器就销毁
                Cookie cookie = new Cookie("auto", null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            Traveller traveller1 = travellerMapper.selectPassword(traveller);
            //用户存在
            //先销毁session中的User对象
            request.getSession().invalidate();
            //将用户信息存储到服务器中
            request.getSession().setAttribute("user", traveller1);
            return "traveller/main";
        } else {
            request.setAttribute("errMsg", "登录失败");
            return "login/traveller";
        }
    }

    @RequestMapping("/main")
    public String mainJsp(HttpServletRequest request) {
        request.getSession();
        return "traveller/main";
    }

    @RequestMapping("/agencyLogin")
    public String toAgencyLogin() {
        return "login/agency";
    }

    @RequestMapping("/adminLogin")
    public String toAdminLogin() {
        return "login/admin";
    }

    @RequestMapping("/toForgetPassword")
    public String toForgetPassword() {
        return "forgetPassword";
    }

    @RequestMapping("/toResetPassword")
    public String toResetPassword() {
        return "traveller/password";
    }

    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register/register";
    }

    @RequestMapping("/register")
    public String addTraveller(Traveller traveller, HttpServletRequest request, HttpServletResponse response) {
        int i = travellerService.addTraveller(traveller);
        if (i != 0) return "login/traveller";
        else {
            request.setAttribute("err_info","注册失败");
            return "register/register";
        }
    }

    //旅客实现注销功能
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
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

    //旅客修改密码
    @RequestMapping("/modifyPassword")
    public String modifyPassword(HttpServletRequest request, HttpServletResponse response) {
        //1.获取参数
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        //2.调用Service查找用户 通过用户id和password查找用户
        Traveller modfiyTra = travellerService.checkPassword(id, password);
        //2.1判断是否有这个用户
        if (modfiyTra == null) {
            //用户不存在，证明密码错误 返回提示信息
            request.setAttribute("err_info", "请确认您的密码正确！！");
            return "";
        } else {
            //用户存在，则修改密码，
            boolean isOk = travellerService.modifyPassword(id, newPassword);
            if (isOk) {
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
            } else {
                //修改失败，返回提示信息
                request.setAttribute("err_info", "修改发生错误，请联系管理员！！");
                return "traveller/password";
            }

        }

    }

    @RequestMapping("/traveller-info")
    public String travellerInfoJsp() {
        return "traveller/traveller-info";
    }

    //旅客修改个人信息
    @RequestMapping("/saveUser")
    public String saveUser(HttpServletRequest request, HttpServletResponse response,Traveller traveller) {
//        Traveller newTraveller = (Traveller) request.getSession().getAttribute("user");
        System.out.println(traveller);
        boolean isTure = travellerService.save(traveller);
        if (isTure) {
            request.getSession().setAttribute("user", traveller);
            request.setAttribute("errMsg", "保存成功！！");
            return "traveller/traveller-info";
        } else {
            request.setAttribute("errMsg", "保存失败，请联系管理员！！");
            return "traveller/traveller-info";
        }

    }

    //旅客查询所有的旅行社
    @RequestMapping("/findAllAgency")
    public String findAllAgency(HttpServletRequest request, HttpServletResponse response) {
        //1.查询所有的旅行社
        List<Agency> agencyList = travellerService.findAllAgency();
        //将查询的结果返回页面
        request.setAttribute("agencies", agencyList);
        return "traveller/agency-list";
    }


    //获取旅行社的详细信息 包括旅行社的联系人 旅行社推荐的旅游路线
    @RequestMapping("/agencyDetail")
    public String agencyDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取Id值
        String id = request.getParameter("id");
        //调用service通过agency的id值查找对应的旅行社
        Agency agency = travellerService.findAgencyById(id);
        //通过agency的id值查找对应的路线
        List<Route> routeList = travellerService.findRouteByAid(id);
        agency.setRouteList(routeList);
        if (agency == null || routeList.isEmpty()) {
            request.setAttribute("errMsg", "查询错误，请联系管理员！");
            return "traveller/agency-list";
        } else {
            request.setAttribute("agency", agency);
            return "traveller/agency-info";
        }
    }

    //旅客选择旅行社推荐的路线
    @RequestMapping("/selectRoute")
    public String selectRoute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.获取routeId and aid
        String routeId = request.getParameter("routeId");
        String aid = request.getParameter("aid");
        //2.调用service查找对应的路线信息和旅行社信息
        //调用service通过agency的id值查找对应的旅行社
        Agency agency = travellerService.findAgencyById(aid);
        Route route = travellerService.findRoute(routeId, aid);
        if (agency == null || route == null) {
            request.setAttribute("errMsg", "查询错误，请联系管理员！");
            return "traveller/agency-info";
        } else {
            request.setAttribute("agency", agency);
            request.setAttribute("route", route);
            return "traveller/order-info";
        }
    }
    //旅客选择好旅游路线之后，填写相关的旅客信息
    @RequestMapping("/fillOrderInfo")
    public String fillOrderInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.获取routeId, aid, tid, 旅客信息
        String aid = request.getParameter("aid");
        String routeId = request.getParameter("routeId");
        //1.1旅客信息
        String trueName = request.getParameter("trueName");
        String phone = request.getParameter("phone");
        String sex = request.getParameter("sex");
        String ID_Card = request.getParameter("ID_Card");
        String beat = request.getParameter("beat");
        double price = Double.parseDouble(request.getParameter("price"));
        //2.调用Service查找身份证号是否已经与有人使用
        Traveller traveller = travellerService.findTraveller(trueName, phone, sex, ID_Card);
        if(traveller == null){
            //拥有这个身份证号的旅客已经存在，返回错误提示信息
            request.setAttribute("errMsg", "该用户不存在，请确认您的信息！");
            return "traveller/order-info";
        }
        //3.根据对应的routeId，aid, tid查找唯一的订单
        AgencyOrder ao = travellerService.findOrderById(aid, routeId, traveller.getId());
        if(ao != null){
            //3.1如果已经产生了对应的订单，则提示错误信息，
            request.setAttribute("errMsg", "您已经选定过，不可以重复选定！！");
            return "traveller/order-info";
        }else {
            //3.2 没有产生过订单，先判断金额是否足够支付，足够支付，则生成新的订单
            if((traveller.getBalance() - price) < 0){
                // 金额不足以支付，提示错误信息
                //3.1如果已经产生了对应的订单，则提示错误信息，
                request.setAttribute("errMsg", "您的【卡内余额】不足，请联系管理员进行充值！！");
                return "traveller/order-info";
            }
            boolean isOk = travellerService.addOrder(aid, routeId, traveller.getId(), beat);
            travellerService.subBalance(traveller, price);
            Traveller newTraveller = (Traveller)request.getSession().getAttribute("user");
            newTraveller.setBalance(traveller.getBalance());
            request.getSession().setAttribute("user", newTraveller);
            if(isOk){
                //订单生成成功，返回 [我的旅行社] 界面
//                response.sendRedirect(request.getContextPath() + "/pages/traveller/myAgency.jsp");
                request.setAttribute("errMsg", "产生订单成功,请到【我的旅行社】进行查看！！");
                return "traveller/order-info";
            }else{
                //订单生成失败，返回提示信息
                request.setAttribute("errMsg", "发生错误，请联系管理员！！");
                return "traveller/order-info";
            }

        }
    }
    //旅客查找自己选择的旅行社
    @RequestMapping("/findMyAgencies")
    public String findMyAgencies(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.根据用户id查找对应的旅行社，旅行路线
        String tid = request.getParameter("tid");
        List<Agency> agencyList = null;
        try {
            agencyList = travellerService.findAgencyOrder(tid);
            if(agencyList.size() == 0){
                //没有查找到对应的订单 返回500页面
                request.setAttribute("errMsg", "您尚未任何旅行社的订单，请到【旅行社信息】-【旅行社列表】进行选择！！");
                return "traveller/myAgency";
            }else{
                //查找到对应的订单，
                request.setAttribute("agencyList", agencyList);
                return "traveller/myAgency";
            }
        } catch (Exception e) {
            return "traveller/500";
        }

    }

    //旅客 查询 自己选择的旅行社的路线 的订单 的详情
    @RequestMapping("/myAgencyOrder")
    public String myAgencyOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.获取传过来的tid,rid,aid，获取响应的route订单信息
        String tid = request.getParameter("tid");
        String aid = request.getParameter("aid");
        String routeId = request.getParameter("rid");
        //2.调用service获取agencyOrder对象
        AgencyOrder ao = null;
        try {
            ao = travellerService.findAgencyOrderByIds(tid, aid, routeId);
            if(ao == null){
                //发生错误，返回500.jsp
                return "traveller/500";
            }else{
                //向myAgencyOrder-info.jsp页面传回ao对象
                request.setAttribute("ao", ao);
                return "traveller/myAgencyOrder-info";
            }
        } catch (Exception e) {
            //发生异常，返回500.jsp
            return "traveller/500";
        }
    }

    //旅客对所选的路线订单进行 退订操作
    @RequestMapping("/exitOrder")
    public String exitOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.获取tid aid rid 来查找对应的订单，并获取当前路线的价格
        String tid = request.getParameter("tid");
        String aid = request.getParameter("aid");
        String routeId = request.getParameter("rid");
        double price = Double.parseDouble(request.getParameter("price"));


        //2.调用service删除对应的订单
        //2.1先判断该用户在这个旅行社是否有订单
        boolean isOk = travellerService.deleteAgencyOrder(tid, aid, routeId);
        if(isOk){
            //2.1 删除成功,将路线对应的价格返回给旅客,并将旅客信息重新存储在session中
            Traveller traveller = null;
            try {
                traveller = travellerService.findTravellerById(tid);
                travellerService.addBalance(traveller, price);
            } catch (Exception e) {
                // 发生错误，返回500.jsp
                return "traveller/500";

            }
            Traveller newTraveller = (Traveller)request.getSession().getAttribute("user");
            newTraveller.setBalance(traveller.getBalance());
            request.getSession().setAttribute("user", newTraveller);
//        2.在页面中心输入退订成功，等待三秒后跳转到myAgency.jsp页面
            return "traveller/myAgency";
        }else {
            //            发生错误，返回500.jsp
            return "traveller/500";
        }
    }

    //获取所有航班的信息
    @RequestMapping("/findAllFlight")
    public String findAllFlight(HttpServletRequest request, HttpServletResponse response)throws Exception{
        //1.调用service查找所有的航班信息
        List<Flight> flightList =travellerService.findAllFlight();
        //2.将航班信息返回到airline-list的航班信息
        request.setAttribute("flightList", flightList);
        return "traveller/airline-list";
    }

    //查找已经支付的订单
    @RequestMapping("/findPayOrder")
    public String findPayOrder(HttpServletRequest request, HttpServletResponse response)throws Exception{
        //1.获取旅客的id
        String tid = request.getParameter("tid");
        List<FlightOrder> flightOrders = null;
        //2.调用service查找已经支付的航班信息
        try {
            flightOrders = travellerService.findPayOrder(tid);
            if(flightOrders.size() == 0){
                request.setAttribute("errMsg", "您尚未有【已支付】的订单！");
                return "traveller/payOrder";
            }else{
                //3.将航班信息返回到airline-list的航班信息
                request.setAttribute("flightOrderList", flightOrders);
                return "traveller/payOrder";
            }
        } catch (Exception e) {
            //发生错误，跳转到错误页面
            return"traveller/500";
        }
    }

    //查找未支付的订单
    @RequestMapping("/findNoPayOrder")
    public String findNoPayOrder(HttpServletRequest request, HttpServletResponse response)throws Exception{
        //1.获取旅客的id
        String tid = request.getParameter("tid");
        List<FlightOrder> flightOrders = null;
        //2.调用service查找已经支付的航班信息
        try {
            flightOrders = travellerService.findNOPayOrder(tid);
            if(flightOrders.size() == 0){
                request.setAttribute("errMsg", "您尚未有【未支付】的订单！");
                return "traveller/noPayOrder";
            }else{
                //3.将航班信息返回到airline-list的航班信息
                request.setAttribute("flightOrderList", flightOrders);
                return "traveller/noPayOrder";
            }
        } catch (Exception e) {
            //发生错误，跳转到错误页面
            return "traveller/500";
        }
    }

    //获取支付的订单信息
    @RequestMapping("/getPayInfo")
    public String getPayInfo(HttpServletRequest request, HttpServletResponse response)throws Exception{
        //1.获取航班订单的id
        String id = request.getParameter("id");
        //2.通过航班订单的id值获取具体的订单信息
        FlightOrder fo = null;
        try {
            fo = travellerService.findFlightOrderById(id);
            //3.将对应的航班信息传递给ticket-info.jsp页面
            request.setAttribute("flightOrder", fo);
            return "traveller/ticket-info";
        } catch (Exception e) {
            return "traveller/500";
        }
    }

    //获取打印信息
    @RequestMapping("/getPrintInfo")
    public String getPrintInfo(HttpServletRequest request, HttpServletResponse response)throws Exception{
        //1.获取航班订单的id
        String id = request.getParameter("id");
        //2.通过航班订单的id值获取具体的订单信息
        FlightOrder fo = null;
        try {
            fo = travellerService.findFlightOrderById(id);

            //3.将对应的航班信息传递给ticket-info.jsp页面
            request.setAttribute("flightOrder", fo);
            return "traveller/invoice";
        } catch (Exception e) {
            return "traveller/500";
        }
    }

    //打印
    @RequestMapping("/printTicket")
    public String printTicket(HttpServletRequest request, HttpServletResponse response)throws Exception{
        //1.获取航班订单的id
        String id = request.getParameter("id");
        //2.通过航班订单的id值获取具体的订单信息
        FlightOrder fo = null;
        try {
            fo = travellerService.findFlightOrderById(id);
            System.out.println(fo.getPayStatusStr());
            //3.将对应的航班信息传递给ticket-info.jsp页面
            request.setAttribute("flightOrder", fo);
            return "traveller/invoice-print";
        } catch (Exception e) {
            return "traveller/500";
        }
    }

    //支付订单
    @RequestMapping("/payOrder")
    public String payOrder(HttpServletRequest request, HttpServletResponse response)throws Exception{
        //1.获取航班订单的id和旅客的tid
        String id = request.getParameter("id");
        String tid = request.getParameter("tid");
        double price = Double.parseDouble(request.getParameter("price"));
        //2.更改订单的支付状态，并将用户的金额数减少
        try {
            travellerService.updateFOrder(id);
            //2.1 向旅客发送取票通知和账单
            //2.1.1 获取订单信息
            FlightOrder fo = travellerService.findFlightOrderById(id);
            // 2.1.2获取当前的用户信息
            Traveller traveller = travellerService.findTravellerById(tid);
            // 2.1.3判断当前的【卡内余额】是否足以支付
            if((traveller.getBalance() - price) < 0){
                // 金额不足以支付，提示错误信息
                request.setAttribute("errMsg", "您的【卡内余额】不足，请联系管理员进行充值！！");
               return "traveller/ticket-info";
            }else{
                Traveller newTraveller = (Traveller)request.getSession().getAttribute("user");
                // 2.1.4 减少对应的金额
                newTraveller.setBalance(traveller.getBalance() - price);
                request.getSession().setAttribute("user", newTraveller);
                // 3.发送取票凭证的提示 邮件
                String content = "【取票凭证】：<br/>" +
                        fo.getTraveller().getTrueName()+ "先生：<br/>" +
                        "  您于" + fo.getPayTimeStr() +"在" + "<a href='http://localhost:8080/Flight/adminController/travellerLogin'>【机票预订系统】</a>" +
                        "中支付了您在【" +fo.getAgency().getAgencyName()+"旅行社】预定的航班机票：<br/>" +
                        "航班编号：" +fo.getFlight().getFlightId() + "      " +
                        "时间：" +  fo.getFlight().getDepartureTime() + "——————" + fo.getFlight().getArrivalTime() + "<br/>" +
                        "城市：" + fo.getFlight().getDepartureCity() + "——————" + fo.getFlight().getFinalCity() +
                        "   舱位等级：" + fo.getBeat() + "  价格：" +fo.getOrderPrice()+ "<br/>" +
                        "<strong>请在飞机起飞前一天前凭取票通知和帐单交款取票。</strong>";
                response.setContentType("text/html;charset=utf-8");
                MailUtils.sendMail(fo.getTraveller().getEmail(), content, "【机票预订系统】取票凭证");
                // 4、返回页面，提示支付成功信息
                request.setAttribute("errMsg","支付成功，请查看【已支付订单】！！我们已向您的邮箱发送了取票凭证，请注意查收。");
                return "traveller/ticket-info";
            }

        } catch (Exception e) {
            return "traveller/500";
        }

    }

    // 航班退票
    @RequestMapping("/exitTicket")
    public String exitTicket(HttpServletRequest request, HttpServletResponse response)throws Exception{
        //1.获取航班订单的id和对应的机票的价格
        String id = request.getParameter("id");
        double price = Double.parseDouble(request.getParameter("price"));

        // 2、删除对应的flightOrder订单
        try {
            travellerService.deleteFlightOrder(id);
            // 3、修改session中用户的【卡内余额】，然后返回main.jsp界面
            Traveller newTraveller = (Traveller)request.getSession().getAttribute("user");
            newTraveller.setBalance(newTraveller.getBalance() + price);
            request.getSession().setAttribute("user", newTraveller);

            return "traveller/main";
        } catch (Exception exception) {
            exception.printStackTrace();
            // 删除失败，返回500错误
            return "traveller/500";
        }

    }

    @RequestMapping("/forgetPassword")
    public String forgetPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.获取参数
        String email = request.getParameter("email");
        //2.调用Service判断邮箱是否正确
        Traveller existUser = null;
        try {
            existUser = travellerService.findByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(existUser == null ){
            //2.1没有查询到用户，说明邮箱不正确
            //返回错误信息，
            request.setAttribute("err_info", "请确认您的邮箱正确！");
            return "forgetPassword";
        }else{
            //2.2邮箱正确，向邮箱发送邮件，并提示用户注意查收邮箱
            //3.激活邮件，发送邮件正文
            String content = "<a href=http://localhost:8080/Flight/travellerController/toresetpassword?id=" + existUser.getId() +">点击重置您在【机票预订系统】中设置的密码</a>";
            MailUtils.sendMail(email, content, "【机票预订系统】重置密码邮件");
            request.setAttribute("err_info", "我们已经向您的邮箱发送了【重置密码】邮件，请注意查收！");
            return "forgetPassword";
        }
    }
    @RequestMapping("/toresetpassword")
    public String toResetassword(HttpServletRequest request, HttpServletResponse response){
        return "resetPassword";
    }


    @RequestMapping("/resetpassword")
    public String resetPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.获取参数
        String id = request.getParameter("id");
        String resetPassword = request.getParameter("resetPassword");
        //2.调用service进行密码重置操作
        boolean isOk = travellerService.modifyPassword(id, resetPassword);
        if(isOk){
            //修改成功
            // 2.1跳转到登陆页面
            return "login/traveller";
        }else{
            //2.2 修改失败，返回提示信息
            request.setAttribute("err_info", "修改发生错误，请联系管理员！！");
            return "resetPassword";
        }
    }
}

