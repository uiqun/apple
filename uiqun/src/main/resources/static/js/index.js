/**
 * Created by David on 2019-01-08.
 */


$(document).ready(function(){
    $("li:even").css({"background-color":"#F0F0F0"});
});

$(function(){
    $("#content>ul>span").html("")
})
function inMfg(rfqno) {
    window.location.href=getRootPath()+"/inMfg/"+rfqno;
}


var w = 1;
var aLRB = document.getElementsByClassName("bannerArrow");

function changeBG(der) {
    if(der==-1){
        --w<0?w=3:"";
    }
    else{
        ++w==4?w=1:"";
    }
    document.getElementsByClassName("bannerMiddle")[0].className="bannerMiddle bannerImage"+w;
}
aLRB[0].onclick = function () {
    changeBG(-1);
}
aLRB[1].onclick = function () {
    changeBG(1);
}
setInterval("changeBG(1)",3000);