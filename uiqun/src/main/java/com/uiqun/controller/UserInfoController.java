package com.uiqun.controller;

import com.uiqun.model.User;
import com.uiqun.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class UserInfoController {
    @Resource
    private UserService userService;


    @RequestMapping("/queryUserDetail")
   public String queryUserDetail(HttpSession session, Model model){
        User user = userService.queryUserDetail( (User)session.getAttribute("user") );
        model.addAttribute("uid",user.getUid());
        model.addAttribute("areas",userService.queryAreas());
        model.addAttribute("nickname",user.getNickname());
        model.addAttribute("company",user.getCompany());
        model.addAttribute("co",user.getCo());
        model.addAttribute("area",user.getArea());
        model.addAttribute("mobile",user.getMobile());
        model.addAttribute("addr",user.getAddr());
        model.addAttribute("contact",user.getContact());
        model.addAttribute("title",user.getTitle());
        model.addAttribute("tel",user.getTel());
        model.addAttribute("QQ",user.getQq());
        model.addAttribute("wechat",user.getWechat());
        model.addAttribute("hobby",user.getHobby());
        model.addAttribute("email",user.getEmail());
        model.addAttribute("website",user.getWebsite());
        model.addAttribute("business",user.getBusiness());
        model.addAttribute("profile",user.getProfile());
        model.addAttribute("certid",user.getCertid());
        model.addAttribute("iscert",user.getIscert());
       return "userInfo";
   }

   @RequestMapping("/updateUser")
   public String updateUser(User user,Model model) {
       if (userService.updateUser(user)) {
           model.addAttribute("AlertMessage", "用户信息修改成功");
       }else {
           model.addAttribute("AlertMessage", "用户信息修改失败");
       }
       return "forward:/queryUserDetail";
   }

  @RequestMapping("/queryUserById")
   public String queryUserById(int id, Model model){
        User user = userService.queryUserById(id);
        model.addAttribute("uid",user.getUid());
      model.addAttribute("nickname",user.getNickname());
      model.addAttribute("mobile",user.getMobile());
      model.addAttribute("company",user.getCompany());
      model.addAttribute("co",user.getCo());
      model.addAttribute("area",user.getArea());
      model.addAttribute("utype",user.getUtype());
      model.addAttribute("contact",user.getContact());
      model.addAttribute("title",user.getTitle());
      model.addAttribute("tel",user.getTel());
      model.addAttribute("QQ",user.getQq());
      model.addAttribute("wechat",user.getWechat());
      model.addAttribute("hobby",user.getHobby());
      model.addAttribute("email",user.getEmail());
      model.addAttribute("addr",user.getAddr());
      model.addAttribute("website",user.getWebsite());
      model.addAttribute("website1",user.getWebsite1());
      model.addAttribute("business",user.getBusiness());
      model.addAttribute("profile",user.getProfile());
      model.addAttribute("ulogo",user.getUlogo());
      model.addAttribute("image",user.getImage());
      model.addAttribute("certid",user.getCertid());
      model.addAttribute("cert",user.getCert());
      model.addAttribute("iscert",user.getIscert());
      model.addAttribute("rrfq",user.getRrfq());
      model.addAttribute("rquote",user.getRquote());
      model.addAttribute("rhot",user.getRhot());
      model.addAttribute("rfind",user.getRfind());
      model.addAttribute("rvendor",user.getRvendor());
      model.addAttribute("rbom",user.getRbom());
       return "forward:/user/Xuser";
   }

    @RequestMapping("/saveUser")
    public String saveUser(User user,Model model) {
        if (userService.saveUser(user)) {
            model.addAttribute("AlertMessage", "用户信息修改成功");
        }else{
            model.addAttribute("AlertMessage", "用户信息修改失败");
        }
        return "forward:/user/Xuser";
    }
}
