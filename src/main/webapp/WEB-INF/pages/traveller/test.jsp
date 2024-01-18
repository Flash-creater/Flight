<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false" errorPage="404.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>
        机票
    </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"><!--图标库-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}https://cdn.bootcdn.net/ajax/libs/font-awesome/5.15.3/css/all.css">
</head>
<body>
<div class="airplane-ticket">
    <div class="ticket header">
        <p>
            <i class="fas fa-meteor"></i>${flightOrder.flight.company}
        </p>
    </div>
    <div class="ticket ticket-left">
        <i class="fas fa-globe-europe"></i>
        <div class="barcode"></div>
        <div class="flight-info-top">
            <div class="name">
                <span>姓名:</span><span>${flightOrder.traveller.trueName}</span>
            </div>
            <div class="date">
                <span>日期:</span><span>${flightOrder.payTimeStr}</span>
            </div>
            <div class="flight">
                <span>航班:</span><span>${flightOrder.flight.flightId}</span>
            </div>
            <div class="gate">
                <span>${flightOrder.beat}</span><span>B3</span>
            </div>
        </div>
        <div class="route">
            <div>
                <p>
                    ${flightOrder.flight.dCode}
                </p><span>${flightOrder.flight.departureCity}:${flightOrder.flight.departureAirport}</span>
            </div><i class="fas fa-plane"></i>
            <div>
                <p>
                    ${flightOrder.flight.aCode}
                </p><span>${flightOrder.flight.finalCity}:${flightOrder.flight.arrivalAirport}</span>
            </div>
        </div>
        <div class="flight-info-bottom">
            <div class="seat">
                <span>座位:</span><span>11E</span>
            </div>
            <div class="group">
                <span>机型:</span><span>${flightOrder.flight.craftType}</span>
            </div>
            <div class="boards">
                <span>起飞时间:</span><span>${flightOrder.flight.departureTime}</span>
            </div>
            <div class="arrives">
                <span>到达时间:</span><span>${flightOrder.flight.arrivalTime}</span>
            </div>
        </div>
    </div>
    <div class="ticket ticket-right">
        <div class="barcode"></div>
        <div class="flight-info">
            <div class="name">
                <span>Name:</span><span>${flightOrder.traveller.trueName}</span>
            </div>
            <div class="date">
                <span>Date:</span><span>${flightOrder.payTimeStr}</span>
            </div>
            <div class="flight">
                <span>Flight:</span><span>${flightOrder.flight.flightId}</span>
            </div>
            <div class="gate">
                <span>Type:</span><span>${flightOrder.flight.craftType}</span>
            </div>
            <div class="seat">
                <span>Seat:</span><span>11E</span>
            </div>
            <div class="group">
                <span>ID_Number:</span><span>${flightOrder.traveller.ID_Card}</span>
            </div>
            <div class="boards">
                <span>Departures:</span><span>${flightOrder.flight.departureTime}</span>
            </div>
            <div class="arrives">
                <span>Arrives:</span><span>${flightOrder.flight.arrivalTime}</span>
            </div>
        </div>
        <div class="route">
            <p>
                ${flightOrder.flight.dCode}
            </p><i class="fas fa-plane"></i>
            <p>
                ${flightOrder.flight.aCode}
            </p>
        </div>
    </div>
    <div class="ticket footer">
        <p>
            <i class="fas fa-meteor"></i>${flightOrder.agency.agencyName}旅行社
        </p>
    </div>
</div>
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