<%--
游客登陆界面
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>登陆页面</title>

    <meta
            content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
            name="viewport">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
</head>

<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <b>机票预定系统</b>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">旅客登陆</p>
        <div class="text-center" style="color:red" id="info">${errMsg}</div>
        <form action="${pageContext.request.contextPath}/login/traveller" method="post">
            <div class="form-group has-feedback">

                <input type="email" name="email" class="form-control"
                       placeholder="邮箱" id="email"> <span
                    class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" name="password" class="form-control"
                       placeholder="密码" id="password"> <span
                    class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8">
                    <div class="checkbox icheck">
                        <label><input type="checkbox" name="auto_login"> 记住 下次自动登录</label>
                    </div>
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat" id="btn">登录</button>
                </div>
                <!-- /.col -->
            </div>
        </form>
        <div class="social-auth-links text-center">
            <p>- 或者 -</p>
            <a href="${pageContext.request.contextPath}/pages/login/agency.jsp" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-cc"></i> 旅行社登录</a>
            <a href="${pageContext.request.contextPath}/pages/login/admin.jsp" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-plane"></i> 航空公司登录</a>
        </div>
        <a href="${pageContext.request.contextPath}/pages/forgetPassword.jsp"  id="forgetPassword">忘记密码</a><br>

        <a href="${pageContext.request.contextPath}/pages/register/register.jsp" class="text-center">新用户注册</a>
    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 2.2.3 -->
<!-- Bootstrap 3.3.6 -->
<!-- iCheck -->
<script
        src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
<script>
    //校验邮箱
    function checkEmail(){
        //1.获取邮箱
        var email = $("#email").val();
        //2.定义正则
        var reg_email = /^\w+@\w+\.\w+$/;
        //3.判断
        var flag = reg_email.test(email);
        if(!flag){
            $("#info").html("请确认您的邮箱格式正确！！！");
        }else {
            $("#info").html(" ");
        }
        return flag;
    }
    function checkPassword(){
        //1.获取密码
        var password = $("#password").val();
        //2.判断密码是否为空
        if(password != null){
            return true;
        }else {
            return false;
        }
    }
    $(function() {
        $('input').iCheck({
            checkboxClass : 'icheckbox_square-blue',
            radioClass : 'iradio_square-blue',
            increaseArea : '20%' // optional
        });
        //对用户邮箱和密码的格式进行检验
        $("#btn").submit(function () {
            if(checkEmail() && checkPassword()){
                return true;
            }else{
                $("#info").html("请确认您的邮箱或密码格式正确！！！");
                return false;
            }
        });
    });
    $("#password").blur(checkPassword);
</script>
</body>

</html>
