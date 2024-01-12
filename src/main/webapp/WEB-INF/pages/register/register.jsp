<%--
旅客、旅行社的
注册页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">


    <title>用户注册</title>


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
        <p class="login-box-msg">新用户注册</p>
        <div class="text-center" style="color:red" id="errMsg">${err_info}</div>
        <form action="${pageContext.request.contextPath}/travellerController/register" method="post" id="registerForm">
            <div class="form-group has-feedback">
                <input type="text" class="form-control" placeholder="用户名" name="userName" id="userName">
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" minlength="8" maxlength="16" placeholder="密码：单词字符长度8-16位" name="password" id="password">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <select id="sex" name="sex" class="form-control">
                    <option value="1">男</option>
                    <option value="2">女</option>
                </select>
<%--                <input type="radio" class="form-control" name="sex" id="man" value="1">男--%>
<%--                <input type="radio" class="form-control" name="sex" id="" value="1">--%>
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
            <input type="email" class="form-control" placeholder="Email" name="email" id="email">
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="text" class="form-control" placeholder="真实姓名" name="trueName" id="trueName">
                <span class="fa  fa-tag form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="text" class="form-control" placeholder="身份证号" name="ID_Card" id="ID_Card">
                <span class="fa  fa-credit-card form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="text" class="form-control" placeholder="手机号" name="phone" id="phone">
                <span class="fa fa-android form-control-feedback"></span>
            </div>
            <div class="row">
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">注册</button>
                </div>
                <!-- /.col -->
            </div>
        </form>


        <a href="${pageContext.request.contextPath}/adminController/travellerLogin" class="text-center">我有账号，现在就去登录</a>
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
    /*
        * 表单校验
        * 1.用户名：单词字符，长度8-20位
        * 2.密码：单词字符 长度8-16位
        * 4.姓名：非空
        * 5.手机号：手机号格式
        * 6.出生日期:非空
        *
        * */
    //校验用户名
    function checkUsername(){
        //1.获取用户值
        var username = $("#userName").val();

        //2.判断 给出提示
        if(username.length > 0){
            //合法
            $("#userName").css("border", "");
            return true;
        }else{
            //非法
            $("#userName").css("border", "1px solid red");
            return false;
        }}
    //校验密码
    function checkPassword(){
        //1.获取用户值
        var password = $("#password").val();
        //2.定义正则
        var reg_password = /[a-zA-Z0-9]{6,}/;
        //3.判断 给出提示
        var flag = reg_password.test(password);
        if(flag){
            //合法
            $("#password").css("border", "");
        }else{
            //非法
            $("#password").css("border", "1px solid red");
        }
        return flag;
    }
    //校验邮箱
    function checkEmail(){
        //1.获取邮箱
        var email = $("#email").val();
        //2.定义正则
        var reg_email = /^\w+@\w+\.\w+$/;
        //3.判断
        var flag = reg_email.test(email);
        if(flag){
            $("#email").css("border", "");
        }else{
            $("#email").css("border", "1px solid red");
        }
        return flag;
    }
    //校验姓名
    function checkName(){
        var name = $("#trueName").val();
        if(name.length > 0){
            //合法
            $("#trueName").css("border", "");
            return true;
        }else{
            //不合法
            $("#trueName").css("border", "1px solid red");
            return false;
        }
    }

    //校验手机号
    function checkPhone(){
        var phone = $("#phone").val();
        var reg_phone = /^[1][3,4,5,7,8][0-9]{9}$/;
        var flag = reg_phone.test(phone)
        if(flag){
            //合法
            $("#phone").css("border", "");
        }else{
            //不合法
            $("#phone").css("border", "1px solid red");
        }
        return flag;
    }
    function testid() {
        var id = $('#ID_Card').val();
            // 1 "验证通过!", 0 //校验不通过 // id为身份证号码
            //var format  = /^([1-6][1-9]|50)\d{4}(18|19|20)\d{2}((0[1-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
            var format = /^(([1][1-5])|([2][1-3])|([3][1-7])|([4][1-6])|([5][0-4])|([6][1-5])|([7][1])|([8][1-2]))\d{4}(([1][9]\d{2})|([2]\d{3}))(([0][1-9])|([1][0-2]))(([0][1-9])|([1-2][0-9])|([3][0-1]))\d{3}[0-9xX]$/;
            //号码规则校验
            if(!format.test(id)){
                //非法
                $("#ID_Card").css("border", "1px solid red");
                return false;
            }
            //区位码校验
            //出生年月日校验  前正则限制起始年份为1900;
            var year = id.substr(6,4),//身份证年
                month = id.substr(10,2),//身份证月
                date = id.substr(12,2),//身份证日
                time = Date.parse(month+'-'+date+'-'+year),//身份证日期时间戳date
                now_time = Date.parse(new Date()),//当前时间戳
                dates = (new Date(year,month,0)).getDate();//身份证当月天数
            if(time>now_time||date>dates){
                //非法
                $("#ID_Card").css("border", "1px solid red");
                return false;
            }
            //校验码判断
            var c = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2);  //系数
            var b = new Array('1','0','X','9','8','7','6','5','4','3','2'); //校验码对照表
            var id_array = id.split("");
            var sum = 0;
            for(var k=0;k<17;k++){
                sum+=parseInt(id_array[k])*parseInt(c[k]);
            }
            if(id_array[17].toUpperCase() != b[sum%11].toUpperCase()){
                //非法
                $("#ID_Card").css("border", "1px solid red");
                return false;
            }

            $("#ID_Card").css("border", " ");
            return true;
    }
    $(function() {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
        //当表单 提交时 调用所有的 校验方法
        $("#registerForm").submit(function(){
            //1.发送异步数据请求
            if(checkPassword()&&checkEmail()&&checkName()&&checkUsername()&&checkPhone()){
                return true;
            }else{
                //方法返回false不提交表单，也不跳转
                $("#errMsg").html("请确认您的操作正确!");
                return false;
            }

        });
        //当某一个组件失去焦点时，调用对应的校验方法
        $("#userName").blur(checkUsername);
        $("#password").blur(checkPassword);
        $("#email").blur(checkEmail);
        $("#trueName").blur(checkName);
        $("#phone").blur(checkPhone);
        //$("#ID_Card").blur(testid);
    });

</script>
</body>
</html>
