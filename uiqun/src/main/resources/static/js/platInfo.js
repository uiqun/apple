/**
 * Created by David on 2019-05-02.
 */

$(function(){
    $("ul li:first-child").click(function(){
        $(this).css("color","#FF0000");
        $(this).siblings().css("color","black");
        $("textarea:first-child").show();
        $("textarea:first-child").siblings().hide();
    })
    $("ul li:nth-child(2)").click(function(){
        $(this).css("color","#FF0000");
        $(this).siblings().css("color","black");
        $("textarea:nth-child(2)").show();
        $("textarea:nth-child(2)").siblings().hide();
    })
    $("ul li:nth-child(3)").click(function(){
        $(this).css("color","#FF0000");
        $(this).siblings().css("color","black");
        $("textarea:nth-child(3)").show();
        $("textarea:nth-child(3)").siblings().hide();
    })
    $("ul li:nth-child(4)").click(function(){
        $(this).css("color","#FF0000");
        $(this).siblings().css("color","black");
        $("textarea:nth-child(4)").show();
        $("textarea:nth-child(4)").siblings().hide();
    })
    $("ul li:nth-child(5)").click(function(){
        $(this).css("color","#FF0000");
        $(this).siblings().css("color","black");
        $("textarea:nth-child(5)").show();
        $("textarea:nth-child(5)").siblings().hide();
    })
})