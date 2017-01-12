<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>GoCheer: Login</title>

    <!--设置网页图标-->
    <link rel="shortcut icon" href="images/logo.png" type=”image/x-icon”/>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/login.css" rel="stylesheet">
    <link href="css/login-state.css" rel="stylesheet">
    <link href="css/input.css" rel="stylesheet">
    <link href="css/header.css" rel="stylesheet">
    <link href="css/footer.css" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="jumbotron row">
    <div class="col-md-4"></div>
    <div class="container col-md-4">
        <form method="post" action="Login" class="input-container">
            <span class="input input--hoshi">
                <input class="input__field input__field--hoshi" type="text" name="username" id="input-1"/>
                <label class="input__label input__label--hoshi input__label--hoshi-color-1" for="input-1">
                    <span class="input__label-content input__label-content--hoshi">Username</span>
                </label>
            </span>

            <span class="input input--hoshi">
                <input class="input__field input__field--hoshi" type="password" name="password" id="input-2"/>
                <label class="input__label input__label--hoshi input__label--hoshi-color-1" for="input-2">
                    <span class="input__label-content input__label-content--hoshi">Password</span>
                </label>
            </span>
            <%
                if(request.getAttribute("wrong")!=null){
                    out.println("<p style=\"color: #E94B00\">Error: Invalid username or password.</p>");
                }
            %>
            <button type="submit" class="btn btn-default btn-lg btn-block btn-login">Login</button>
        </form>
    </div>
</div>
<jsp:include page="footer.jsp"/>


<script src="js/classie.js"></script>
<script>
    (function () {
        // trim polyfill : https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/Trim
        if (!String.prototype.trim) {
            (function () {
                // Make sure we trim BOM and NBSP
                var rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
                String.prototype.trim = function () {
                    return this.replace(rtrim, '');
                };
            })();
        }

        [].slice.call(document.querySelectorAll('input.input__field')).forEach(function (inputEl) {
            // in case the input is already filled..
            if (inputEl.value.trim() !== '') {
                classie.add(inputEl.parentNode, 'input--filled');
            }

            // events:
            inputEl.addEventListener('focus', onInputFocus);
            inputEl.addEventListener('blur', onInputBlur);
        });

        function onInputFocus(ev) {
            classie.add(ev.target.parentNode, 'input--filled');
        }

        function onInputBlur(ev) {
            if (ev.target.value.trim() === '') {
                classie.remove(ev.target.parentNode, 'input--filled');
            }
        }
    })();
</script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="js/header.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="http://v3.bootcss.com/assets/js/ie10-viewport-bug-workaround.js"></script>
</body>

</html>