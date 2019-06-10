package com.uiqun.controller;

import com.alibaba.fastjson.JSON;
import com.uiqun.model.Hotstk;
import com.uiqun.model.User;
import com.uiqun.service.HotstkService;
import com.uiqun.service.UserService;
import com.uiqun.utils.MessageUtil;
import com.uiqun.utils.Pager;
import com.uiqun.utils.VoResponseJson;
import com.yunpian.sdk.YunpianException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Resource
    private HotstkService hotstkService;

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
        VoResponseJson vrj = new VoResponseJson();
        if(userService.queryUserByPhone(mobile)){
            //获取随机码长度
            String randomNumber = MessageUtil.getRandomNumber(4);

            try {
                //发送短信消息
                MessageUtil.singleSend(MessageUtil.getApiKey(), "【大唯科技】您的验证码是" + randomNumber, mobile);
                //保存验证码
                session.setAttribute("verifyCode",randomNumber);
            }catch (Exception e){
                e.printStackTrace();
                //发送失败
                vrj.setErrorCode(1001);
            }
        }else{
            vrj.setErrorCode(1111);
        }
        return JSON.toJSONString(vrj);
    }
    @RequestMapping("/regist")
    public String regist(){
        return "regist";
    }

    /**
     * 用户注册
     * @param session
     * @param user
     * @param veriCode
     * @param model
     * @return
     */
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

    @RequestMapping("/Xuser")
    public String Xuser(){
        return "Xuser";
    }

    @RequestMapping("/company/{id}")
    public String showCompany(@PathVariable int id, Pager<Hotstk> pager, Model model) throws Exception{
        pager.getCondition().put("uid",id);
        model.addAttribute("page",hotstkService.queryHotstks(pager));
        model.addAttribute("company",userService.queryUserById(id).getCompany());
        model.addAttribute("addr",userService.queryUserById(id).getAddr());
        model.addAttribute("contact",userService.queryUserById(id).getContact());
        model.addAttribute("tel",userService.queryUserById(id).getTel());
        model.addAttribute("wechat",userService.queryUserById(id).getWechat());
        model.addAttribute("website",userService.queryUserById(id).getWebsite());
        model.addAttribute("business",userService.queryUserById(id).getBusiness());
        model.addAttribute("profile",userService.queryUserById(id).getProfile());
        return "company";
    }
}
