<%--
 我选择的旅行社的订单详情
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" errorPage="404.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>旅行社信息</title>
    <meta name="description" content="旅行社信息">
    <meta name="keywords" content="旅行社信息">

    <!-- Tell the browser to be responsive to screen width -->
    <meta
            content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
            name="viewport">

    <link rel=“stylesheet”
          href="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/morris/morris.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.theme.default.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/select2/select2.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/adminLTE/css/skins/_all-skins.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.skinNice.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap-slider/slider.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">

    <!-- 页面头部 -->
    <jsp:include page="header.jsp"><jsp:param name="user" value="${user}"/></jsp:include>
    <!-- 页面头部 /-->

    <!-- 导航侧栏 -->
    <jsp:include page="aside.jsp"><jsp:param name="user" value="${user}"/></jsp:include>
    <!-- 导航侧栏 /-->

    <!-- 内容区域 -->
    <div class="content-wrapper">

        <!-- 内容头部 -->
        <section class="content-header">
            <h1>
                旅行社信息 <small>旅行社列表</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i
                        class="fa fa-dashboard"></i> 首页</a></li>
                <li><a href="#">旅行社信息</a></li>
                <li class="active">旅行社列表</li>
            </ol>
        </section>
        <!-- 内容头部 /-->
<%--        提交预定旅行社的用户信息--%>
        <form action="${pageContext.request.contextPath}/traveller/fillOrderInfo"
              method="post" id="fillForm">
        <!-- 正文区域 -->
        <section class="content"> <!--订单信息-->
            <div class="panel panel-default">
                <div class="panel-heading">订单信息</div>
                <div class="row data-type">

                    <div class="col-md-2 title">旅行社编号</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="旅行社编号"
                               value="${agency.id}" readonly="readonly" name="aid">
                    </div>
                    <div class="col-md-2 title">旅行社名称</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="旅行社名称"
                               value="${agency.agencyName}" readonly="readonly" name="agencyName">
                    </div>

                    <div class="col-md-2 title">路线编号</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="路线编号"
                               value="${route.routeId}" readonly="readonly" name="routeId">
                    </div>
                    <div class="col-md-2 title">路线名称</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="路线名称"
                               value="${route.routeName}" readonly="readonly" name="routeName">
                    </div>

                    <div class="col-md-2 title">出发城市</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="出发城市"
                               value="${route.departureCity}" readonly="readonly" name="departureCity">
                    </div>

                    <div class="col-md-2 title">出发时间</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" class="form-control pull-right"
                                   id="datepicker-a6" value="${route.departureTimeStr}"
                                   readonly="readonly">
                        </div>
                    </div>

                </div>
            </div>
            <!--订单信息/--> <!--游客信息-->
            <div class="panel panel-default">
                <div class="panel-heading" style="color:red">请填写游客信息</div>
                <!--数据列表-->
                <table id="dataList"
                       class="table table-bordered table-striped table-hover dataTable">
                    <thead>
                    <tr>
                        <th class="">人群</th>
                        <th class="">姓名</th>
                        <th class="">性别</th>
                        <th class="">手机号码</th>
                        <th class="">证件类型</th>
                        <th class="">证件号码</th>
                        <th>舱位等级</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>成人</td>
                            <td>
                                <input type="text" size="10" value="${user.trueName}" name="trueName" id="trueName" class="form-control">
                            </td>
                            <td>
                                    <select class="form-control select2" style="width: 100%"
                                            name="sex" id="sex">
                                        <option value="1" id="male">男</option>
                                        <option value="0" id="female">女</option>
                                    </select>
                            </td>
                            <td><input type="text" size="20"
                                       value="${user.phone}" name="phone" id="phone" class="form-control" ></td>
                            <td><input type="text" size="15"
                                       value="居民身份证" readonly="readonly" class="form-control">
                            </td>
                            <td>
                                <input type="text" size="28" value="${user.ID_Card}" name="ID_Card" id="ID_Card" class="form-control">
                            </td>
                            <td>
                                <select class="form-control select2" style="width: 100%"
                                        name="beat" id="beat">
                                    <option value="头等舱" id="firstClass" selected>头等舱</option>
                                    <option value="商务舱" id="businessClass">商务舱</option>
                                    <option value="经济舱" id="economyClass">经济舱</option>
                                </select>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <!--数据列表/-->
            </div>
            <!--游客信息/-->
            <!--费用信息-->
                <div class="panel panel-default">
                    <div class="panel-heading">费用信息</div>
                    <div class="row data-type">

                        <div class="col-md-2 title">支付方式</div>
                        <div class="col-md-4 data text">在线支付-卡内余额</div>

                        <div class="col-md-2 title">金额</div>
                        <div class="col-md-4 data">
                            <div class="input-group">
                                <span class="input-group-addon">¥</span>
                                <input type="text" class="form-control" value="${route.price}" name="price" readonly="readonly"/>
                                <span class="input-group-addon">.00</span>
                            </div>
                        </div>

                    </div>
                </div>
            <!--费用信息/--> <!--工具栏-->
            <div class="box-tools text-center">
                <button type="submit" class="btn bg-default" id="isGo">提交</button>
                <button type="button" class="btn bg-default"
                        onclick="history.back(-1);">返回</button>
            </div>
            <!--工具栏/--> </section>
        <!-- 正文区域 /-->

        </form>
        <div class="text-center" style="color:red" id="errMsg">${errMsg}</div>
    </div>
    <!-- 内容区域 /-->

    <!-- 底部导航 -->
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 1.0.1
        </div>
        <strong>Copyright &copy; 2020-2021 <a
                href="http://www.baidu.cn">版权所有：何志森项目组</a>
        </strong>地址：山西省晋中市榆次区乌金山镇明向校区 | 邮政编码：030600 | 电话：0351-5222222</footer>
    <!-- 底部导航 /-->

</div>

<script
        src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/jQueryUI/jquery-ui.min.js"></script>
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/raphael/raphael-min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/morris/morris.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/sparkline/jquery.sparkline.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/knob/jquery.knob.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/daterangepicker/moment.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/adminLTE/js/app.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/markdown.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/to-markdown.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/chartjs/Chart.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.resize.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.pie.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.categories.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-slider/bootstrap-slider.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>

<script>
    $(document).ready(function() {
        // 选择框
        $(".select2").select2();

        // WYSIHTML5编辑器
        $(".textarea").wysihtml5({
            locale : 'zh-CN'
        });
    });

    // 设置激活菜单
    function setSidebarActive(tagUri) {
        var liObj = $("#" + tagUri);
        if (liObj.length > 0) {
            liObj.parent().parent().addClass("active");
            liObj.addClass("active");
        }
    }
    //对用户信息进行校验
    //对用户的身份证好进行检验
    function testid() {
        var id = $('#ID_Card').val();
            // 1 "验证通过!", 0 //校验不通过 // id为身份证号码
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
            //非法
            $("#ID_Card").css("border", " ");
            return true;
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
    $(document).ready(function() {
        // 判断当前路线是否出发
        $("#isGo").click(function () {
            var nowTime = new Date();
            var time = '${route.departureTimeStr}'; // 获取路线出发的时间
            var orderTime = new Date(time);
            if(orderTime.getTime() < nowTime.getTime()){
                alert("该路线已出发，请重新选择路线！！")
                return false;
            }
            if(confirm("请确认您的操作")){
                return true;
            } else {
                return false;
            }
        });
        //对性别进行判断
        if(${user.sex}){
            $("#sex").attr("true","1");
        }else{
            $("#sex").attr("true","0");
        }
        //当某一个组件失去焦点时，调用对应的【用户信息】的校验方法
        $("#ID_Card").blur(testid);
        $("#trueName").blur(checkName);
        $("#phone").blur(checkPhone);
        //当提交表单的时候，调用submit方法进行校验
        $("#fillForm").submit(function () {
            if(checkPhone()&&checkName()&&testid()){
                //所有的信息都正确
                return true;
            }else{
                //存在信息不正确
                $("#errMsg").html("请确认您填写的信息都正确！！！");
                return false;
            }
        });
        // 激活导航位置
        setSidebarActive("order-manage");

        // 列表按钮
        $("#dataList td input[type='checkbox']").iCheck({
            checkboxClass : 'icheckbox_square-blue',
            increaseArea : '20%'
        });
        // 全选操作
        $("#selall").click(function() {
            var clicks = $(this).is(':checked');
            if (!clicks) {
                $("#dataList td input[type='checkbox']").iCheck("uncheck");
            } else {
                $("#dataList td input[type='checkbox']").iCheck("check");
            }
            $(this).data("clicks", !clicks);
        });
    });
</script>
</body>


</html>
