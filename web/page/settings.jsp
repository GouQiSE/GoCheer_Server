<%--
  Created by IntelliJ IDEA.
  User: Donggu
  Date: 2016/12/20
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div class="tab-pane" id="settings">
    <div class="alert alert-success" role="alert">Please input the blocks you want to update.</div>
    <form class="form-horizontal" role="form" action="../update" method="post">
        <div class="form-group">
            <label for="inputPassword3" class="col-md-4 control-label">New Password</label>
            <div class="col-md-4">
                <input name="password" type="password" class="form-control" id="inputPassword3"
                       placeholder="New Password" onblur="checkPassword()">
            </div>
            <div class="col-md-4 warn">
                <span id="warning4"></span>
            </div>
        </div>

        <div class="form-group">
            <label for="inputConfirm3" class="col-md-4 control-label">Confirm New Password</label>
            <div class="col-md-4">
                <input type="password" class="form-control" id="inputConfirm3" placeholder="Confirm New Password"
                       onblur="check()">
            </div>
            <div class="col-md-4 warn">
                <span id="warning"></span>
            </div>
        </div>


        <div class="form-group">
            <label for="inputConfirm3" class="col-md-4 control-label">New Gender</label>
            <div class="col-md-4">
                <label class="radio-inline">
                    <input type="radio" name="gender" id="inlineRadio1" value="true" onclick="checkForButton()"> Male
                </label>
                <label class="radio-inline">
                    <input type="radio" name="gender" id="inlineRadio2" value="false" onclick="checkForButton()"> Female
                </label>
            </div>
        </div>
        <div class="form-group">
            <label for="inputNickname3" class="col-md-4 control-label">New Alias</label>
            <div class="col-md-4">
                <input name="alias" type="name" class="form-control" id="inputNickname3" placeholder="New Alias" onblur="checkNickname()">
            </div>
            <div class="col-md-4 warn">
                <span id="warning2"></span>
            </div>
        </div>
        <div class="form-group">
            <label for="inputEmail3" class="col-md-4 control-label">New Email</label>
            <div class="col-md-4">
                <input name="email" type="email" class="form-control" id="inputEmail3" placeholder="New Email" onblur="checkEmail()">
            </div>
            <div class="col-md-4 warn">
                <span id="warning5"></span>
            </div>
        </div>

        <div class="update">
            <span><button type="submit" class="btn btn-default" id="btn-update" disabled="disabled">Update</button></span>
        </div>
    </form>
</div>
<%--<script>--%>
    <%--function check() {--%>
        <%--if (document.getElementById("inputPassword3").value !=--%>
                <%--document.getElementById("inputConfirm3").value) {--%>
            <%--document.getElementById("warning").innerHTML = "   The two passwords do not match.";--%>
            <%--document.getElementById("btn-update").setAttribute("disabled","disabled");--%>
        <%--} else {--%>
            <%--document.getElementById("warning").innerHTML = "   ";--%>
            <%--document.getElementById("btn-update").removeAttribute("disabled");--%>
        <%--}--%>
    <%--}--%>
<%--</script>--%>
<script src="../js/checkUpdate.js"></script>
</body>
</html>
