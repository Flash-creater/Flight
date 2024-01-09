<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/1/4
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>机票预订</title>
  </head>
  <script type="text/javascript">
      function directUrl() {
          window.location.href="${pageContext.request.contextPath}/pages/traveller/main.jsp";
          return;
      }
  </script>
  <body onload="directUrl()">

  </body>
</html>
