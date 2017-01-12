<%@ page import="entity.User" %><%--
  Created by IntelliJ IDEA.
  User: Donggu
  Date: 2016/12/3
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hi</title>
</head>
<body>
<%
    String username = (String)request.getSession().getAttribute("user");
    if(username == null){
        response.sendRedirect("/login.jsp");
    }
    else{
        out.println(username);
    }
%>
</body>
</html>
