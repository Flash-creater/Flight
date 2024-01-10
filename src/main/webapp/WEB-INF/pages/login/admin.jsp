<%--
管理员登陆界面
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
        <p class="login-box-msg">航空公司登陆</p>
        <div class="text-center" style="color:red" id="errMsg">${errMsg}</div>
        <form action="${pageContext.request.contextPath}/admin/isLogin" method="post">
            <div class="form-group has-feedback">
                <input type="text" name="name" class="form-control" id="name"
                       placeholder="用户名"> <span
                    class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" name="password" class="form-control" id="password"
                       placeholder="密码" maxlength="16"> <span
                    class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat" id="btn">登录</button>
                </div>
                <!-- /.col -->
            </div>
        </form>
        <div class="social-auth-links text-center">
            <p>- 或者 -</p>
            <a href="${pageContext.request.contextPath}/pages/login/traveller.jsp" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-male"></i> 旅客登录</a>
            <a href="${pageContext.request.contextPath}/pages/login/agency.jsp" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-cc"></i> 旅行社登录</a>
        </div>
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
    function checkName(){
        //1.获取旅行社名称
        var name = $("#name").val();
        //2.判断旅行社名称是否为空
        if(name != null){
            return true;
        }else {
            return false;
        }
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
        $("#btn").submit(function () {
            if(checkPassword() && checkName()){
                return true;
            }else{
                $("#errMsg").html("请输入您的名称或密码！！")
                return false;
            }
        })
    });
</script>
</body>

</html>
