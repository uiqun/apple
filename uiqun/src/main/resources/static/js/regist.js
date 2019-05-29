/**
 * Created by David on 2019-01-02.
 */

$(document).ready(function(){

    $("form").submit(function(){
        var username = $("#username").val();
            if(username==""){
                alert("�û��� ����Ϊ��")
                return false;
        }
        if(username.length<6){
            alert("�û�������Ҫ6λ")
            return false;
        }
        var password= $("#passWord").val();
        if(password==""){
            alert("����������")
            return false;
        }
        if(password.length<6){
            alert("��������Ҫ6λ")
            return false;
        }

        var handPhone = $("#handPhone").val();
        var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
        if(handPhone==""){
            alert("�������ֻ�����")
            return false;
        }

        if(!myreg.test($handPhone)){
            alert("��������ֻ������ʽ���ԣ�����������")
            return false;
        }

        var veriCode = $("veriCode").val();
        if(veriCode==""){
            alert("��������֤��")
            return false;
        }
    })
})


function verifyCode(obj) {
    var mobile = obj.previousElementSibling.value;
    var b = /^1[34578]\d{9}$/.test(mobile);
    if(b){
        $.post("/user/registerVerify",{"mobile":mobile},function (result) {
            if(result.errorCode==0000){
                alert("发送成功,验证码仅在一分钟内有效!");
            }else if(result.errorCode==1111){
                alert("手机号已注册！请登录");
            }
        },"JSON")
}
}

function registerUser() {
    var isAgree = $("input[name=isAgree]:checked").val();
    var username= $("input[name=nickname]").val();
    var pwd= $("input[name=pwd]").val();
    var veriCode= $("input[name=veriCode]").val();
    if(isAgree=="1" && /^[a-zA-Z][a-zA-Z0-9]{3,15}$/.test(username)
    && /[A-Za-z0-9]{4,10}/.test(pwd)&&/\d{4}/.test(veriCode)){
        document.registForm.submit();
    }else{
        alert("请正确填写信息");
    }
}
