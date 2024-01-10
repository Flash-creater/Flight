<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%--
  Created by IntelliJ IDEA.
  User: bill
  Date: 12/19/23
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>航班数据全览</title>
</head>
<body>
<h3 align="center">航班信息全览</h3>
<table>
  <tr>
    <th>航班号</th>
    <th>出发城市</th>
    <th>目的城市</th>
    <th>航空公司</th>
    <th>机型</th>
    <th>出发机场</th>
    <th>出发机场三字码</th>
    <th>目的机场</th>
    <th>目的机场三字码</th>
    <th>起飞时间</th>
    <th>降落时间</th>
    <th>经济舱价格</th>
    <th>商务舱价格</th>
    <th>头等舱价格</th>
  </tr>
  <c:forEach items="${flights}" var="flight">
    <tr>
      <td>${flight.flightId}</td>
      <td>${flight.departureCity}</td>
      <td>${flight.finalCity}</td>
      <td>${flight.company}</td>
      <td>${flight.craftType}</td>
      <td>${flight.departureAirport}</td>
      <td>${flight.dCode}</td>
      <td>${flight.arrivalAirport}</td>
      <td>${flight.aCode}</td>
      <td>${flight.departureTime}</td>
      <td>${flight.arrivalTime}</td>
      <td>${flight.economyPrice}</td>
      <td>${flight.businessPrice}</td>
      <td>${flight.firstPrice}</td>
      <td>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
