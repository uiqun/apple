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





$(function(){
    $('.myscroll').myScroll({
        speed: 80, //数值越大，速度越慢
        rowHeight: 28 //tr的高度
    });
});

(function($){
    $.fn.myScroll = function(options){
        //默认配置
        var defaults = {
            speed:80,  //滚动速度,值越大速度越慢
            rowHeight:28 //每行的高度
        };

        var opts = $.extend({}, defaults, options),intId = [];

        function marquee(obj, step){

            obj.find("table").animate({
                marginTop: '-=1'
            },0,function(){
                var s = Math.abs(parseInt($(this).css("margin-top")));
                if(s >= step){
                    $(this).find("tr").slice(0, 1).appendTo($(this));
                    $(this).css("margin-top", 0);
                }
            });
        }

        this.each(function(i){
            var sh = opts["rowHeight"],speed = opts["speed"],_this = $(this);
            intId[i] = setInterval(function(){
                if(_this.find("table").height()<=_this.height()){
                    clearInterval(intId[i]);
                }else{
                    marquee(_this, sh);
                }
            }, speed);

            _this.hover(function(){
                clearInterval(intId[i]);
            },function(){
                intId[i] = setInterval(function(){
                    if(_this.find("table").height()<=_this.height()){
                        clearInterval(intId[i]);
                    }else{
                        marquee(_this, sh);
                    }
                }, speed);
            });

        });

    }

})(jQuery);


$(function(){
    $("#content>ul>span").html("")
})

function inquote(rfqno) {
    window.location.href=getRootPath()+"/inquote/"+rfqno;
}





