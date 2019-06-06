window.onload = roll(100);

function roll(t) {
    var ul1 = document.getElementById("ul1");
    var ul2 = document.getElementById("ul2");
    var box = document.getElementById("box");
    ul2.innerHTML = ul1.innerHTML;
    box.scrollTop = 0;
    var timer = setInterval(rollStart, t);
    box.onmouseover = function () {
        clearInterval(timer)
    }
    box.onmouseout = function () {
        timer = setInterval(rollStart, t);
    }
}

function rollStart() {
    if (box.scrollTop >= ul1.scrollHeight) {
        box.scrollTop = 0;
    } else {
        box.scrollTop++;
    }
}



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




