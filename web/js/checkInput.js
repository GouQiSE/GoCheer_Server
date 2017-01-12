/**
 * Created by hubeini on 2016/12/21.
 */

var userNameError=false;
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
    if(nickname.replace(/[^\x00-\xff]/g,"**").length > 15 ) {
        document.getElementById("warning2").innerHTML = "输入的用户名过长";
        nickNmeError=true;

    }
    else{
        document.getElementById("warning2").innerHTML = "";
        nickNmeError=false;
        //checkFinish();
        //return true;
    }
    checkForButton();
}

function checkUsername() {
    var username = document.getElementById("inputUsername3").value;
    switch( isUsername( username ) ){
        case 0: break;
        case 1: {
            changeUsernamePrompt( "格式不正确，用户名不能以数字开头" );
            userNameError=true;
            checkForButton();

            return false;
        }
        case 2: {
            changeUsernamePrompt( "字符长度有误，合法长度为6-20个字符" );
            userNameError=true;
            checkForButton();

            return false;
        }
        case 3: {
            changeUsernamePrompt( "含有非法字符，用户名只能包含_,英文字母，数字" );
            userNameError=true;
            checkForButton();

            return false;
        }
        case 4: {
            changeUsernamePrompt("格式不正确，用户名只能包含_,英文字母，数字" );
            userNameError=true;
            checkForButton();

            return false;
        }
        case 5:{
            changeUsernamePrompt("该用户名已被占用 " );
            userNameError=true;
            checkForButton();

            return false;
        }
    }
    document.getElementById("warning3").innerHTML = "   ";
    userNameError=false;
    //return true;
    checkForButton();
}
function changeUsernamePrompt(cnt){
    document.getElementById( "warning3" ).innerHTML = cnt;
    document.getElementById( "warning3" ).style.display = "";
}
function isUsername( username ){
    if( /^\d.*$/.test( username ) ){
        return 1;
    }
    if(! /^.{6,20}$/.test( username ) ){
        return 2;
    }
    if(! /^[\w_]*$/.test( username ) ){
        return 3;
    }
    if(! /^([a-z]|[A-Z])[\w_]{5,19}$/.test( username ) ){
        return 4;
    }
    sendRequest(username);
    return 0;
}

function checkPassword() {
    var id = document.getElementById("inputPassword3");
    var password=id.value;
    if(password.length < 6){
        document.getElementById("warning4").innerHTML = "密码长度需大于6位";
        passWordError=true;

    }
    else {
        document.getElementById("warning4").innerHTML = "   ";
        passWordError=false;
        //checkFinish();
        //return true;
    }
    checkForButton();
}

function checkEmail()
{

    var id = document.getElementById("inputEmail3");
    var email = id.value;

    var expr = /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/;
    if (!expr.test(email)) {
        document.getElementById("warning5").innerHTML = "输入的邮箱格式有误";
        emailError=true;

    }
    else {
        document.getElementById("warning5").innerHTML = " ";
        emailError=false;
        //checkFinish();
        //return true;
    }
    checkForButton();
}

function checkFinish()
{
    var password = document.getElementById("inputPassword3").value;
    var email = document.getElementById("inputEmail3").value;
    var confirm= document.getElementById("inputConfirm3").value;
    var nickname = document.getElementById("inputNickname3").value;
    var username=document.getElementById("inputUsername3").value;
    var isMale=document.getElementById("inlineRadio1").checked;
    var isFemale=document.getElementById("inlineRadio2").checked;
    if(username &&password && confirm && nickname&&email&&(isMale||isFemale)) {
        return true;
    }
    else
        return false;
}

function checkError(){
    if(userNameError||passWordError||confirmError||nickNmeError||emailError)
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
        document.getElementById("btn-signup").disabled=false;
    }
    else
    {
        document.getElementById("btn-signup").disabled=true;
    }
}

function sendRequest(){
    var username=document.getElementById("inputUsername3").value;

    var settings = {
     // "async": true,
     "crossDomain": false,
     "url": "/checkUsername?username="+username,
     "method": "GET",
     "headers": {
     "cache-control": "no-cache",
     "postman-token": "c973a20a-9f0d-db25-5010-4ccdd5177489"
     }
     }

     $.ajax(settings).done(function (response) {
     var obj=eval(response);
     if(obj==true){
         document.getElementById("warning3").innerHTML=" ";
         userNameError=false;
         checkForButton();
     }
     else{
        document.getElementById("warning3").innerHTML="该用户名已被占用";
         userNameError=true;
         checkForButton();
     }
     });
}

