/**
 * Created by David on 2019-04-27.
 */

$(function(){
    $("ul li").mouseover(function(){
        $(this).css("color","#FF0000");
        $(this).find("ol").show();
    }).mouseout(function(){
        $(this).css("color","black");
        $(this).find("ol").hide();
    })
})

