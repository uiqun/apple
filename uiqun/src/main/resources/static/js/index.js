var wrap = document.querySelector(".wrap");
var next = document.querySelector(".arrow_right");
var prev = document.querySelector(".arrow_left");
next.onclick = function () {
    next_pic();
}
prev.onclick = function () {
    prev_pic();
}
function next_pic () {
    index++;
    if(index > 4){
        index = 0;
    }
    showCurrentDot();
    var newLeft;
    if(wrap.style.left === "-2820px"){
        newLeft = -940;
    }else{
        newLeft = parseInt(wrap.style.left)-470;
    }
    wrap.style.left = newLeft + "px";
}
function prev_pic () {
    index--;
    if(index < 0){
        index = 4;
    }
    showCurrentDot();
    var newLeft;
    if(wrap.style.left === "0px"){
        newLeft = -1880;
    }else{
        newLeft = parseInt(wrap.style.left)+470;
    }
    wrap.style.left = newLeft + "px";
}
var timer = null;
function autoPlay () {
    timer = setInterval(function () {
        next_pic();
    },2000);
}
autoPlay();

var container = document.querySelector(".container");
container.onmouseenter = function () {
    clearInterval(timer);
}
container.onmouseleave = function () {
    autoPlay();
}

var index = 0;
var dots = document.getElementsByTagName("span");
function showCurrentDot () {
    for(var i = 0, len = dots.length; i < len; i++){
        dots[i].className = "";
    }
    dots[index].className = "on";
}

for (var i = 0, len = dots.length; i < len; i++){
    (function(i){
        dots[i].onclick = function () {
            var dis = index - i;
            if(index == 4 && parseInt(wrap.style.left)!==-2350){
                dis = dis - 5;
            }
            //和使用prev和next相同，在最开始的照片5和最终的照片1在使用时会出现问题，导致符号和位数的出错，做相应地处理即可
            if(index == 0 && parseInt(wrap.style.left)!== -470){
                dis = 5 + dis;
            }
            wrap.style.left = (parseInt(wrap.style.left) +  dis * 470)+"px";
            index = i;
            showCurrentDot();
        }
    })(i);
}





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




