<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false" errorPage="404.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>
        CSS3绘制的飞机票网页代码 - www.dedemao.com
    </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"><!--图标库-->
    <link rel="stylesheet" href="https://cdn.bootcdn.net/ajax/libs/font-awesome/5.15.3/css/all.css">
</head>
<body>
<div class="airplane-ticket">
    <div class="ticket header">
        <p>
            <i class="fas fa-meteor"></i>Admit One
        </p>
    </div>
    <div class="ticket ticket-left">
        <i class="fas fa-globe-europe"></i>
        <div class="barcode"></div>
        <div class="flight-info-top">
            <div class="name">
                <span>姓名：:</span><span>${flightOrder.traveller.trueName}</span>
            </div>
            <div class="date">
                <span>日期:</span><span>2021/02/01</span>
            </div>
            <div class="flight">
                <span>航班:</span><span>${flightOrder.flight.flightId}</span>
            </div>
            <div class="gate">
                <span>头等舱:</span><span>B3</span>
            </div>
        </div>
        <div class="route">
            <div>
                <p>
                    LHR
                </p><span>伦敦希思罗机场</span>
            </div><i class="fas fa-plane"></i>
            <div>
                <p>
                    IBZ
                </p><span>伊比萨机场</span>
            </div>
        </div>
        <div class="flight-info-bottom">
            <div class="seat">
                <span>座位:</span><span>11E</span>
            </div>
            <div class="group">
                <span>组:</span><span>3</span>
            </div>
            <div class="boards">
                <span>起飞时间:</span><span>10:25</span>
            </div>
            <div class="arrives">
                <span>到达时间:</span><span>13:05</span>
            </div>
        </div>
    </div>
    <div class="ticket ticket-right">
        <div class="barcode"></div>
        <div class="flight-info">
            <div class="name">
                <span>Name:</span><span>Neville Longbottom</span>
            </div>
            <div class="date">
                <span>Date:</span><span>2021/02/01</span>
            </div>
            <div class="flight">
                <span>Flight:</span><span>2005</span>
            </div>
            <div class="gate">
                <span>Gate:</span><span>B3</span>
            </div>
            <div class="seat">
                <span>Seat:</span><span>11E</span>
            </div>
            <div class="group">
                <span>Group:</span><span>3</span>
            </div>
            <div class="boards">
                <span>Boards:</span><span>10:25</span>
            </div>
            <div class="arrives">
                <span>Arrives:</span><span>13:05</span>
            </div>
        </div>
        <div class="route">
            <p>
                LHR
            </p><i class="fas fa-plane"></i>
            <p>
                IBZ
            </p>
        </div>
    </div>
    <div class="ticket footer">
        <p>
            <i class="fas fa-meteor"></i>dedemao.COM
        </p>
    </div>
</div>
</body>
</html>