package com.uiqun.controller;

import com.alibaba.fastjson.JSON;
import com.uiqun.model.User;
import com.uiqun.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController  {
    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public String login(User user,HttpServletRequest request){
        if(user!=null&&user.getNickname()!=null&&!"".equals(user.getNickname())){
            User login = userService.login(user);
            if(login!=null) {
                request.getSession().setAttribute("user", login);
                return "redirect:/index";
            }else{
                return "login";
            }
        }
        return "login";
    }

    @RequestMapping(value="/queryUserInfo",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String queryUserInfo(Integer userId){
        User user = userService.queryUserById(userId);
        if(user!=null) {
            return JSON.toJSONString(user);
        }
        return null;
    }


    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }


}
