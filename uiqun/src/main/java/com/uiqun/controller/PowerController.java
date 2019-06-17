package com.uiqun.controller;

import com.uiqun.constant.PowerConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限控制器
 */
@Controller
public class PowerController {
    @RequestMapping("/tips")
    public String tips(HttpServletRequest request, Model model){
        int status = (int)request.getAttribute("status");
        //错误页面信息
        if(status== PowerConstant.SERVER_ERROR){
            model.addAttribute("TipsMessage","该服务正在维护,请稍后重试");
        }
        //权限不足信息
        else if(status==PowerConstant.PERMISSION_DENIED){
            model.addAttribute("TipsMessage","用户权限不足,权限开通请咨询管理员");
        }
        //找不到页面
        else if(status==PowerConstant.NOT_FIND_PAGE){
            model.addAttribute("TipsMessage","页面跑丢了");
        }
        return "tipsPage";
    }
}
