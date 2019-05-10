/**
 * Created by David on 2019-01-08.
 */

function checkPn(obj) {
    var pn = obj.value;
    var mfg = document.getElementsByName("mfg")[0].value;
    obj.nextElementSibling.style.fontSize="12px";
    if(mfg==null||mfg==''){
        obj.nextElementSibling.style.color="red";
        obj.nextElementSibling.innerHTML="品牌不能为空";
        return;
    }
    if(pn==null||pn==''){
        obj.nextElementSibling.style.color="red";
        obj.nextElementSibling.innerHTML="型号不能为空";
        return;
    }
    $.post("/checkPn",{"pn":pn,"mfg":mfg},function (date){
        if(date.success=="none"){
            obj.nextElementSibling.style.color="red";
            obj.nextElementSibling.innerHTML="品牌不存在";
        }else if(date.success=="yes"){
            obj.nextElementSibling.style.color="green";
            obj.nextElementSibling.innerHTML="可添加型号";
        }else if(date.success=="no"){
            obj.nextElementSibling.style.color="red";
            obj.nextElementSibling.innerHTML="型号已存在";
        }
    },"json")
}
function checkMfg(obj) {
    var mfgName = obj.value;
    obj.nextElementSibling.nextElementSibling.style.fontSize="12px";
    if(mfgName==null||mfgName==''){
        obj.nextElementSibling.nextElementSibling.style.color="red";
        obj.nextElementSibling.nextElementSibling.innerHTML="品牌不能为空";
        return;
    }
    $.post("/checkMfg",{"mfgName":mfgName},function (date){
        if(date.success=="yes"){
            obj.nextElementSibling.nextElementSibling.style.color="red";
            obj.nextElementSibling.nextElementSibling.innerHTML="品牌不存在,请添加品牌";
        }else{
            obj.nextElementSibling.nextElementSibling.style.color="green";
            obj.nextElementSibling.nextElementSibling.innerHTML="已正确匹配品牌";
        }
    },"json")
}

function saveCurrentPn() {
    var pn = document.getElementsByName("pn")[0].value;
    var mfg = document.getElementsByName("mfg")[0].value;
    if(pn!=''&&pn!=null&&mfg!=''&&mfg!=null){
        document.addForm.submit();
    }
}