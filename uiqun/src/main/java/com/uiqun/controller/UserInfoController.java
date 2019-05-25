package com.uiqun.controller;

import com.uiqun.model.User;
import com.uiqun.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class UserInfoController {
    @Resource
    private UserService userService;

    @RequestMapping("/queryUserDetail")
   public String queryUserDetail(User user, Model model){
       model.addAttribute("user",userService.queryUserDetail(user));
       return "userInfo";
   }

   public String updateUser(User user,Model model) {
       if (userService.updateUser(user)) {
           model.addAttribute("AlertMessage", "用户信息修改成功");
       }
       model.addAttribute("AlertMessage", "用户信息修改失败");
       return "forward:/queryUserDetail";
   }
}
