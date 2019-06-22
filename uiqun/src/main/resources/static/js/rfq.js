/**
 * Created by David on 2019-01-08.
 */

$(document).ready(function(){
    $("li:odd").css("background","#F0F0F0")
})
function checkRfqPn(){
     var pn = document.getElementsByName("pn")[0].value;
    if(pn==null||pn==''){
        document.getElementById("alert").innerHTML="型号不能为空";
        return;
    }else {
        document.getElementById("alert").innerHTML="";
    }
    $.post("/checkRfqPn",{"pn":pn},function (date){
        if(date.state=="none"){
            document.getElementById("alert").innerHTML=date.message;
            document.getElementById("create").style.display="block";
            document.getElementById("mfg").innerHTML="品牌";
        }else{
            if(date.datas.length>0) {
                var tdSelect = document.getElementById("mfg");
                tdSelect.innerHTML = "";
                var select = document.createElement("select");
                select.setAttribute("name", "mfg");
                select.style.fontSize="18px";
                tdSelect.append(select);
                for (i = 0; i < date.datas.length; i++) {
                    var option = document.createElement("option");
                    option.setAttribute("value", date.datas[i].mfgName);
                    option.innerText = date.datas[i].mfgName;
                    option.style.fontSize="18px";
                    tdSelect.children[0].append(option);
                }
                document.getElementById("alert").innerHTML="";
                document.getElementById("create").style.display="none";
            }
        }

    },"json")
}

function checkRfqPnX(){
    var pn = document.getElementsByName("pn")[0].value;
    if(pn==null||pn==''){
        document.getElementById("AlertMessage").value="型号不能为空";
        return;
    }else {
        document.getElementById("AlertMessage").value="";
    }
    $.post("/checkRfqPnX",{"pn":pn},function (date){
        if(date.state=="none"){
            document.getElementById("AlertMessage").value=date.message;
            AlertMessageX();
        }else{
            if(date.datas.length>0) {
                var tdSelect = document.getElementsByName("mfg")[0];
                tdSelect.value = "";
                tdSelect.style.fontSize="18px";
                for (i = 0; i < date.datas.length; i++) {
                    var option = document.createElement("option");
                    option.setAttribute("value", date.datas[i].mfg);
                    option.innerText = date.datas[i].mfgName;
                    option.style.fontSize="18px";
                    tdSelect.append(option);
                }
                document.getElementById("AlertMessage").value="";
            }
        }

    },"json")
}

function commitRfq(num) {
    document.getElementById('isOpen').value=num;
    var qty = document.getElementsByName("qty")[0].value;
    var tp = document.getElementsByName("tp")[0].value;
    var dtime = document.getElementsByName("dtime")[0].value;


    if(document.getElementsByName("mfg")!=null&&document.getElementsByName("mfg").length>0
        &&(qty.match(/\d+/))
        &&(tp.match(/\d{1,5}[\.]?\d{0,3}/))
        &&(dtime!=null&&dtime!='')){
        document.rfqSubmit.submit();
    }else {
        alert("请填写询价的相关信息");
    }
}