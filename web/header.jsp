<%@ page import="dao.UserDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cn">
<body>
<div class="menu">
    <ul>
        <li><a href="/index.jsp"><img src="/images/logo-min.png"></a></li>
        <div id="menu2" class="menu">
            <ul>
                <li><a href="/index.jsp">HOME</a></li>
                <li><a href="/leaderboard.jsp">LEADERBOARD</a></li>
                <ul class="navR pull-right">
                    <%
                        String user = (String)request.getSession().getAttribute("user");
                        if(user==null){
                            out.println("<li><a href=\"/login\">Log In</a></li>");
                            out.println("<li><a href=\"/signup\">Sign Up</a></li>");
                        }
                        else{
                            out.println("<li><a href=\"/logout\">Log Out</a></li>");
                            out.println("<li><a href=\"/home/"+user+"\">"+ UserDAO.getInstance().findById(user).getAlias()+"</a></li>");
                        }
                    %>
                </ul>
            </ul>
        </div>
    </ul>
</div>
</body>
</html>