<%--
  用户修改密码
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="404.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">


    <title>密码修改</title>


    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <!-- Font Awesome -->
    <!-- Ionicons -->
    <!-- Theme style -->
    <!-- iCheck -->
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">

</head>

<body class="hold-transition register-page">
<div class="register-box">
    <div class="register-logo">


        <a href="#"><b>机票预定</b>管理系统</a>


    </div>

    <div class="register-box-body">
        <p class="login-box-msg">用户密码修改</p>
        <div class="text-center" style="color:red" id="errMsg">${err_info}</div>
        <form action="${pageContext.request.contextPath}/travellerController/modifyPassword?id=${user.id}" method="post" id="modifyForm">
            <div class="form-group has-feedback">
                <input type="password" class="form-control" placeholder="原密码" name="password" id="password">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" minlength="8" max="16" placeholder="修改密码" name="newPassword" id="modify">
                <span class="fa  fa-tag form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" minlength="8" max="16" placeholder="确认修改密码" name="password_ok" id="sure">
                <span class="fa  fa-credit-card form-control-feedback"></span>
            </div>
            <div class="row">
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">确定</button>
                </div>
                <!-- /.col -->
            </div>
        </form>

    </div>
    <!-- /.form-box -->
</div>
<!-- /.register-box -->

<!-- jQuery 2.2.3 -->
<!-- Bootstrap 3.3.6 -->
<!-- iCheck -->
<script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
<script>
    $(function() {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
        function checkPassword(){
            var modify = $('#modify').val();
            var sure = $('#sure').val();
            if(modify==sure && modify != null){
                //修改的密码和确认的密码一致
                $('#sure').css('border', '');
                return true;
            }else{
                //修改的密码和确认的密码一致
                $('#sure').css('border', '1px solid red');
                return false;
            }
        };
        $('#modifyForm').submit(function () {
            if(checkPassword()){
                return  true;
            }else{
                $("#errMsg").html("请确认您的操作正确!");
                return false;
            }
        });
        $('#sure').blur(checkPassword);
    });
</script>
</body>
</html>

