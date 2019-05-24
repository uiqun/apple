package com.uiqun.controller;

import com.alibaba.fastjson.JSON;
import com.uiqun.model.User;
import com.uiqun.service.UserService;
import com.uiqun.utils.MessageUtil;
import com.uiqun.utils.VoResponseJson;
import com.yunpian.sdk.YunpianException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    /**
     * 发短信验证码
     * @param session
     * @param mobile
     * @return
     * @throws YunpianException
     */
    @RequestMapping(value = "/registerVerify",method = RequestMethod.POST)
    @ResponseBody
    public String register(HttpSession session, String mobile) throws YunpianException {
        //获取随机码长度
        String randomNumber = MessageUtil.getRandomNumber(4);
        VoResponseJson vrj = new VoResponseJson();
        try {
            //发送短信消息
            MessageUtil.singleSend(MessageUtil.getApiKey(), "【易电商城】您的验证码是" + randomNumber, mobile);
            //保存验证码
            session.setAttribute("verifyCode",randomNumber);
        }catch (Exception e){
            e.printStackTrace();
            //发送失败
            vrj.setErrorCode(1001);
        }
        return JSON.toJSONString(vrj);
    }
    @RequestMapping("/regist")
    public String regist(){
        return "regist";
    }

    @RequestMapping(value = "/addregist",method =RequestMethod.POST)
    public String addregist(HttpSession session, User user, String veriCode, Model model){
        Object verifyCode = session.getAttribute("verifyCode");
        if(verifyCode!=null&&verifyCode.equals(veriCode)){
            if(userService.register(user)){
                return "login";
            }
        }
        return "regist";
    }
}
