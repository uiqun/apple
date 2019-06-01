

$(function(){
    $("#content>ul>span").html("")
})

function inquote(rfqno) {
    window.location.href=getRootPath()+"/inquote/"+rfqno;
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