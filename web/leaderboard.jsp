<%@ page import="java.util.List" %>
<%@ page import="dao.UserDAO" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="entity.User" %>
<%@ page import="org.w3c.dom.Document" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>GoCheer: Leader board</title>

    <!--设置网页图标-->
    <link rel="shortcut icon" href="images/logo.png" type=”image/x-icon”/>
    <!-- Bootstrap -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/header.css" rel="stylesheet">
    <link href="css/footer.css" rel="stylesheet">
    <link href="css/leader-board.css" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp"/>
<%
    String user = (String) request.getSession().getAttribute("user");
    List leaderBoard = UserDAO.getInstance().getLeaderboard();
%>
<div class="leader-board container">
    <div class="row  col-md-10 col-md-offset-1">
        <div class="hint panel panel-info">
            <div class="panel-heading">
                <span>Here is the leader board.</span>
                <a href="#user" id="user-hint" class="pull-right"><B>MY POSITION</B></a>
            </div>
        </div>
        <%
            int last_score = -1;
            int last_rank = -1;
            int standard = 5;
            for (int i = 0; i < leaderBoard.size(); i++) {
                User u = (User) leaderBoard.get(i);
                out.println("<div "+ (u.getUsername().equals(user)?"id=\"user\" ":"")+"class=\"panel-body ");
                if (u.getScore() == last_score) {
                    out.println(((last_rank < standard) ? " panel-top" : " panel-normal") + "\">");
                    out.println("<div class=\"col-md-1 position\">");
                    out.println("<B class=\"number" + ((last_rank < standard ) ? "-top" : "") + "\">" + (last_rank + 1) + "</B>");
                } else {
                    out.println(((i < standard) ? " panel-top" : "") + "\">");
                    out.println("<div class=\"col-md-1 position\">");
                    out.println("<B class=\"number" + ((i < standard) ? "-top" : "") + "\">" + (i + 1) + "</B>");
                    last_score = u.getScore();
                    last_rank = i;
                }
                out.println("</div>\n" +
                        "<div class=\"col-md-1 suffix-top\">");
                out.println("<B class=\"suffix-top\">\"</B>");
                out.println("</div>\n" +
                        "<div class=\"alias col-md-7\">");
                if (last_rank < standard) {
                    out.println("<div class=\"media\"><div class=\"media-left\">");
                    out.println("<span class=\"name-top\"><a href=\"home/"+u.getUsername()+"\">" + u.getAlias() + "</a></span>");
                    out.println("</div><div class=\"media-body label-list\">");
                    out.println("<span class=\"label label-warning\">" + u.getWordsum() + " words</span><br>");
                    out.println("<span class=\"label label-warning\">" + u.getScoresum() + " achievements</span>");
                    out.println("</div></div></div>");
                    out.println("<div class=\"col-md-3 number-top score\">");
                } else {
                    out.println("<h4><B><a href=\"home/"+u.getUsername()+"\">" + u.getAlias() + "</a></B></h4>");
                    out.println("<span class=\"label label-default\">" + u.getWordsum() + " words</span>");
                    out.println("<span class=\"label label-default\">" + u.getScoresum() + " achievements</span></div>");
                    out.println("<div class=\"col-md-3 number score\">");
                }
                out.println("<B>" + u.getScore() + "</B>");
                out.println("</div></div>");
            }
        %>
    </div>
</div>

<jsp:include page="footer.jsp"/>
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="js/header.js"></script>
<script>
    if(document.getElementById("user")==null){
        $("#user-hint").hide();
    }
</script>
</body>
</html>