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
