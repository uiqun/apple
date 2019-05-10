/**
 * Created by David on 2019-01-08.
 */

$(document).ready(function(){
    $("li:odd").css("background","#F0F0F0")

})
function checkRfqPn(){
     var pn = document.getElementsByName("pn")[0].value;
    if(pn==null||pn==''){
        alert("型号不能为空");
        return;
    }
    $.post("/checkRfqPn",{"pn":pn},function (date){
        if(date.state=="none"){
            alert("型号不存在");
        }else{
            var tdSelect = document.getElementById("mfg");
            tdSelect.append("<select name=\"mfg\"></select>");
            for (i=0;i<date.datas.size();i++){
                var option = '<option value="'+data.datas[i].mfgName+'">'+data.datas[i].mfgName+'</option>';
                tdSelect.children()[0].append(option);
            }
        }

    },"json")
}