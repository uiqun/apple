/**
 * Created by David on 2019-01-02.
 */

$(document).ready(function(){

    $("form").submit(function(){
        var username = $("#username").val();
            if(username==""){
                alert("用户名 不能为空")
                return false;
        }
        if(username.length<6){
            alert("用户名至少要6位")
            return false;
        }
        var password= $("#passWord").val();
        if(password==""){
            alert("请输入密码")
            return false;
        }
        if(password.length<6){
            alert("密码至少要6位")
            return false;
        }

        var handPhone = $("#handPhone").val();
        var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
        if(handPhone==""){
            alert("请输入手机号码")
            return false;
        }

        if(!myreg.test($handPhone)){
            alert("您输入的手机号码格式不对，请重新输入")
            return false;
        }

        var veriCode = $("veriCode").val();
        if(veriCode==""){
            alert("请输入验证码")
            return false;
        }
    })
})
