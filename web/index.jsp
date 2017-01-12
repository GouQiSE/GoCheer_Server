<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>GoCheer: A Google Chrome Extensiton</title>

    <!--设置网页图标-->
    <link rel="shortcut icon" href="images/logo.png" type=”image/x-icon”/>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/header.css" rel="stylesheet">
    <link href="css/footer.css" rel="stylesheet">
    <link href="css/index.css" rel="stylesheet">
</head>

<body>
<jsp:include page="header.jsp"/>
<div id="jb1">
    <a type="button"  href="GoCheer.crx" class="btn btn-default">Download GoCheer</a>
</div>
<div id="jb2">
    <img src="images/jb2.png">
    <img src="images/jb3.png">
    <img src="images/jb4.png">
    <img src="images/jb5.png">
</div>
<jsp:include page="footer.jsp"/>
<script>
    function changeBg() {
        var bg=document.getElementById("jb1");
        bg.style.transition = 'background-color 10000ms';
        function change() {
            var h = Math.round(Math.random()*360);
            while ((h>35&&h<130)||(h>175&&h<340)) h = Math.round(Math.random()*360);
            var s = Math.round(Math.random()*40+30);
            var l = Math.round(Math.random()*40+30);
            bg.style.backgroundColor = "HSL("+h+", "+s+"%, "+l+"%)";
            setTimeout(change, 10000);
        }
        change()
    }
    changeBg();
</script>

<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="js/header.js"></script>
</body>
</html>