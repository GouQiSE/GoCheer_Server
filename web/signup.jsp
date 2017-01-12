<%--
  Created by IntelliJ IDEA.
  User: orient
  Date: 2016/12/8
  Time: 下午1:57
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>GoCheer: Sign up</title>

    <!--设置网页图标-->
    <link rel="shortcut icon" href="images/logo.png" type=”image/x-icon”/>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/footer.css" rel="stylesheet">
    <link href="css/header.css" rel="stylesheet">
    <link href="css/signup.css" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="jumbotron">
    <div class="container">
        <form class="form-horizontal" role="form" action="/signup" method="post">
            <div class="form-group">
                <label for="inputUsername3" class="col-md-4 control-label">Username</label>
                <div class="col-md-4">
                    <input name="username" type="username" class="form-control" id="inputUsername3"
                           placeholder="Username" onblur="checkUsername()">
                </div>
                <div class="col-md-4 warn">
                    <span id="warning3"></span>
                </div>
            </div>

            <div class="form-group">
                <label for="inputPassword3" class="col-md-4 control-label">Password</label>
                <div class="col-md-4">
                    <input name="password" type="password" class="form-control" id="inputPassword3"
                           placeholder="Password" onblur="checkPassword()">
                </div>
                <div class="col-md-4 warn">
                    <span id="warning4"></span>
                </div>
            </div>

            <div class="form-group">
                <label for="inputConfirm3" class="col-md-4 control-label">Confirm Password</label>
                <div class="col-md-4">
                    <input type="password" class="form-control" id="inputConfirm3" placeholder="Confirm Password"
                           onblur="check()">
                </div>
                <div class="col-md-4 warn">
                    <span id="warning"></span>
                </div>
            </div>


            <div class="form-group">
                <label for="inputConfirm3" class="col-md-4 control-label">Gender</label>
                <div class="col-md-4">
                    <label class="radio-inline">
                        <input type="radio" name="gender" id="inlineRadio1" value="1" onclick="checkForButton()"> Male
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="gender" id="inlineRadio2" value="0" onclick="checkForButton()"> Female
                    </label>
                </div>
            </div>
            <div class="form-group">
                <label for="inputNickname3" class="col-md-4 control-label">Nickname</label>
                <div class="col-md-4">
                    <input name="alias" type="name" class="form-control" id="inputNickname3" placeholder="Nickname" onblur="checkNickname()">
                </div>
                <div class="col-md-4 warn">
                    <span id="warning2"></span>
                </div>
            </div>
            <div class="form-group">
                <label for="inputEmail3" class="col-md-4 control-label">Email</label>
                <div class="col-md-4">
                    <input name="email" type="email" class="form-control" id="inputEmail3" placeholder="Email" onblur="checkEmail()">
                </div>
                <div class="col-md-4 warn">
                    <span id="warning5"></span>
                </div>
            </div>

            <div class="sign-up">
                <span><button type="submit" id="btn-signup" class="btn btn-default" disabled="true">Sign up</button></span>
            </div>
        </form>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
<%--<script>--%>
    <%--function check() {--%>
        <%--if (document.getElementById("inputPassword3").value !=--%>
                <%--document.getElementById("inputConfirm3").value) {--%>
            <%--document.getElementById("warning").innerHTML = "   The two passwords do not match.";--%>
            <%--document.getElementById("btn-signup").setAttribute("disabled","disabled");--%>
        <%--} else {--%>
            <%--document.getElementById("warning").innerHTML = "   ";--%>
            <%--document.getElementById("btn-signup").removeAttribute("disabled");--%>
        <%--}--%>
    <%--}--%>
<%--</script>--%>
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="js/header.js"></script>
<script src="js/checkInput.js"></script>
</html>