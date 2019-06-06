/**
 * Created by David on 2019-05-02.
 */

$(function(){
    $("ul li:first-child").click(function(){
        $(this).css("color","#FF0000");
        $(this).siblings().css("color","black");
        $("p:first-child").show();
        $("p:first-child").siblings().hide();
    })
    $("ul li:nth-child(2)").click(function(){
        $(this).css("color","#FF0000");
        $(this).siblings().css("color","black");
        $("p:nth-child(2)").show();
        $("p:nth-child(2)").siblings().hide();
    })
    $("ul li:nth-child(3)").click(function(){
        $(this).css("color","#FF0000");
        $(this).siblings().css("color","black");
        $("p:nth-child(3)").show();
        $("p:nth-child(3)").siblings().hide();
    })
    $("ul li:nth-child(4)").click(function(){
        $(this).css("color","#FF0000");
        $(this).siblings().css("color","black");
        $("p:nth-child(4)").show();
        $("p:nth-child(4)").siblings().hide();
    })
    $("ul li:nth-child(5)").click(function(){
        $(this).css("color","#FF0000");
        $(this).siblings().css("color","black");
        $("p:nth-child(5)").show();
        $("p:nth-child(5)").siblings().hide();
    })
})