/**
 * Created by David on 2019-01-08.
 */

$(document).ready(function(){
    $("li:odd").css("background","#F0F0F0")

})
function checkRfqPn(){
     var pn = document.getElementsByName("pn")[0].value;
    if(pn==null||pn==''){
        document.getElementById("mfg").innerHTML="请查询型号选择品牌";
        return;
    }
    $.post("/checkRfqPn",{"pn":pn},function (date){
        if(date.state=="none"){
            document.getElementById("mfg").innerHTML=date.message;
        }else{
            if(date.datas.length>0) {
                var tdSelect = document.getElementById("mfg");
                tdSelect.innerHTML = "";
                var select = document.createElement("select");
                select.setAttribute("name", "mfg");
                tdSelect.append(select);
                for (i = 0; i < date.datas.length; i++) {
                    var option = document.createElement("option");
                    option.setAttribute("value", date.datas[i].mfgName);
                    option.innerText = date.datas[i].mfgName;
                    tdSelect.children[0].append(option);
                }
            }
        }

    },"json")
}