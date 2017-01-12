/**
 * Created by hubeini on 2016/12/21.
 */

var passWordError=false;
var confirmError=false;
var nickNmeError=false;
var emailError=false;


function check() {
    if (document.getElementById("inputPassword3").value !=
        document.getElementById("inputConfirm3").value) {
        document.getElementById("warning").innerHTML = "两次密码的输入不一致";
        confirmError=true;

    } else {
        document.getElementById("warning").innerHTML = "";
        confirmError=false;
        //return true;
    }
    checkForButton();
}

function checkNickname()
{
    //只简单的判断昵称的长度
    var id = document.getElementById("inputNickname3");
    var nickname=id.value;
    if(!nickname.length>0 && nickname.replace(/[^\x00-\xff]/g,"**").length > 15 ) {
        document.getElementById("warning2").innerHTML = "输入的用户名过长";
        nickNmeError=true;

    }
    else{
        document.getElementById("warning2").innerHTML = "";
        nickNmeError=false;
    }
    checkForButton();
}

function checkPassword() {
    var id = document.getElementById("inputPassword3");
    var password=id.value;
    if(password.length>0 && password.length < 6){
        document.getElementById("warning4").innerHTML = "密码长度需大于6位";
        passWordError=true;
    }
    else {
        document.getElementById("warning4").innerHTML = "   ";
        passWordError=false;
    }
    checkForButton();
}

function checkEmail()
{

    var id = document.getElementById("inputEmail3");
    var email = id.value;

    var expr = /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/;
    if (email.length>0 && !expr.test(email)) {
        document.getElementById("warning5").innerHTML = "输入的邮箱格式有误";
        emailError=true;

    }
    else {
        document.getElementById("warning5").innerHTML = " ";
        emailError=false;
    }
    checkForButton();
}

function checkFinish()
{
    var password = document.getElementById("inputPassword3").value;
    var email = document.getElementById("inputEmail3").value;
    var confirm= document.getElementById("inputConfirm3").value;
    var nickname = document.getElementById("inputNickname3").value;
    var isMale=document.getElementById("inlineRadio1").checked;
    var isFemale=document.getElementById("inlineRadio2").checked;
    if((password && confirm) || nickname || email ||(isMale||isFemale)) {
        return true;
    }
    else
        return false;
}

function checkError(){
    if(passWordError||confirmError||nickNmeError||emailError)
    {
        return false;
    }
    else
    {
        return true;
    }
}
function checkForButton(){
    if(checkFinish()&&checkError()){
        document.getElementById("btn-update").disabled=false;
    }
    else
    {
        document.getElementById("btn-update").disabled=true;
    }
}


