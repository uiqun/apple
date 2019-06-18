/**
 * Created by David on 2019-04-27.
 */

$(function(){
    $("ul li").click(function(){
        $(this).css("color","#FF0000");
        $(this).find("ol").show();
        $(this).siblings().css("color","black");
        $(this).siblings().find("ol").hide();
    })
})
var checkCurrentUserInfoVar =null;
function checkCurrentUserInfo(obj) {
    $(obj)[0].onmousemove=function (evt) {
        var oEvent = evt || window.event;
        var scrollleft = document.documentElement.scrollLeft || document.body.scrollLeft;
        var scrolltop = document.documentElement.scrollTop || document.body.scrollTop;
        if(checkCurrentUserInfoVar==null||checkCurrentUserInfoVar.uid!=$(obj).attr("value")) {
            $.post("/user/queryUserInfo", {"userId": $(obj).attr("value")}, function (result) {
                if (result != null) {
                    checkCurrentUserInfoVar=result;
                }
            }, "JSON")
        }
        $("#currentUserInfo thead th").html(checkCurrentUserInfoVar.company==null?"":checkCurrentUserInfoVar.company);
        $("#currentUserInfo tbody>tr:first-child>td:nth-of-type(2)").html(checkCurrentUserInfoVar.contact==null?"":checkCurrentUserInfoVar.contact);
        $("#currentUserInfo tbody>tr:first-child>td:nth-of-type(4)").html(checkCurrentUserInfoVar.tel==null?"":checkCurrentUserInfoVar.tel);
        $("#currentUserInfo tbody>tr:nth-of-type(2)>td:nth-of-type(2)").html(checkCurrentUserInfoVar.qq==null?"":checkCurrentUserInfoVar.qq);
        $("#currentUserInfo tbody>tr:nth-of-type(2)>td:nth-of-type(4)").html(checkCurrentUserInfoVar.wechat==null?"":checkCurrentUserInfoVar.wechat);
        $("#currentUserInfo tfoot td:nth-of-type(2)").html(checkCurrentUserInfoVar.addr==null?"":checkCurrentUserInfoVar.addr);
        document.getElementById("currentUserInfo").style.display = "block";
        document.getElementById("currentUserInfo").style.left = oEvent.clientX + scrollleft + 10 + "px";
        document.getElementById("currentUserInfo").style.top = oEvent.clientY + scrolltop + 10 + "px";
    }
}

function uncheckCurrentUserInfo() {
    document.getElementById("currentUserInfo").style.display = "none";
}






