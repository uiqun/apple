package com.uiqun.controller;

import com.uiqun.model.User;
import com.uiqun.model.Utype;
import com.uiqun.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController  {
    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public String login(User user, HttpSession session){
        if(user!=null&&user.getNickname()!=null&&!"".equals(user.getNickname())){
            User login = userService.login(user);
            if(login!=null) {
                session.setAttribute("user", login);
                return "redirect:/index";
            }else{
                return "login";
            }
        }
        return "login";
    }

    @RequestMapping("/queryVendor/{utype}")
    public String queryVendor(Model model, @RequestParam("utype") Utype utype){
        List<User> users = userService.queryVendor(utype);
        model.addAttribute("vendors",users);
        return "forward:/queryUtypes";
    }
}
