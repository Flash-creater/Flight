<%--
   航班的机票打印
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" errorPage="404.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>打印</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <!-- Font Awesome -->
    <!-- Ionicons -->
    <!-- Theme style -->
    <!-- jQuery 2.2.3 -->
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/AdminLTE/css/AdminLTE.min.css">
</head>

<body>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Ticket
        </h1>
    </section>
    <!-- Main content -->
    <section class="invoice">
        <!-- title row -->
        <div class="row">
            <div class="col-xs-12">
                <h2 class="page-header">
                    <i class="fa fa-globe"></i> 机票预订系统.
                    <small class="pull-right" >Date: <span class="time">2/10/2014</span></small>
                </h2>
            </div>
            <!-- /.col -->
        </div>
        <!-- info row -->
        <div class="row invoice-info">
            <div class="col-sm-4 invoice-col">
                From&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;To
                <address>
                    <strong>${flightOrder.flight.departureCity}</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>${flightOrder.flight.finalCity}</strong><br>
                    ${flightOrder.flight.departureTimeStr}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${flightOrder.flight.arrivalTimeStr}<br>
                    ${flightOrder.flight.departureCity}机场, CA 94107&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp${flightOrder.flight.finalCity}机场, CA 94107<br>
                </address>
            </div>
            <!-- /.col -->
            <div class="col-sm-4 invoice-col">
                <b>Traveller</b><br>
                <b>Name:</b> ${flightOrder.traveller.trueName}<br>
                <b>Email:</b> ${flightOrder.traveller.email}<br>
                <b>Phone:</b> ${flightOrder.traveller.phone}
            </div>
            <!-- /.col -->
            <div class="col-sm-4 invoice-col">
                <b>Ticket</b><br>
                <b>Order ID:</b> ${flightOrder.id}<br>
                <b>Payment Due:</b>${flightOrder.payTimeStr}<br>
                <b>Flight ID:</b> ${flightOrder.flight.flightId}
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->

        <!-- Table row -->
        <div class="row">
            <div class="col-xs-12 table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>agency</th>
                        <th>Serial #</th>
                        <th>Description</th>
                        <th>contactName</th>
                        <th>contactPhone</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>${flightOrder.agency.agencyName}</td>
                        <td>${flightOrder.agency.id}</td>
                        <td>${flightOrder.agency.agencyInfo}</td>
                        <td>${flightOrder.agency.contactName}</td>
                        <td>${flightOrder.agency.contactPhone}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->

        <div class="row">
            <!-- accepted payments column -->
            <div class="col-xs-6">
                <p class="lead">Payment Methods:</p>


                <p class="text-muted well well-sm no-shadow" style="margin-top: 10px;">
                    【机票预订系统】-----【卡内余额】
                </p>
            </div>
            <!-- /.col -->
            <div class="col-xs-6">
                <p class="lead">Amount Due <span class="time">2/22/2014</span></p>

                <div class="table-responsive">
                    <table class="table">
                        <tr>
                            <th style="width:50%">Total:</th>
                            <td>￥${flightOrder.orderPrice}</td>
                        </tr>
                    </table>
                </div>
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->
    </section>
    <!-- /.content -->
    <div class="clearfix"></div>
</div><!-- ./wrapper -->

<script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script>
    $(document).ready(function() {
        //获取当前的时间
        var nowTime = new Date();
        var year = nowTime.getFullYear();
        var month = nowTime.getMonth() + 1;
        var day = nowTime.getDay() + 1;
        nowTime = year + "/" + month + "/"+day;
        $(".time").html(nowTime);
        // 延迟1秒打印，等待图片载入
        setTimeout(function() {
            window.print();
        }, 1000);
    });
</script>
</body>

</html>