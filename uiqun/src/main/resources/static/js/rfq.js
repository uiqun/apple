/**
 * Created by David on 2019-01-08.
 */

$(document).ready(function(){
    $("li:odd").css("background","#F0F0F0")

})
function checkPn(){
     var pn = document.getElementsByName("pn")[0].value;
    if(pn==null||pn==''){
        alert("型号不能为空");
        return;
    }
    $.post("/checkPn",{"pn":pn},function (date){
        if(date.state=="none"){
            alert("型号不存在");
        }else{
            var tdSelect = document.getElementById("mfg");
            <select name="mfg"></select>
            for (i=0;i<date.datas.size();i++){

            }
        }

    },"json")
}