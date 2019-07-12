package com.uiqun.controller;

import com.alibaba.fastjson.JSON;
import com.uiqun.dao.AreaDao;
import com.uiqun.model.Hotstk;
import com.uiqun.model.User;
import com.uiqun.service.HotstkService;
import com.uiqun.service.QltytypeService;
import com.uiqun.service.UserService;
import com.uiqun.utils.*;
import com.yunpian.sdk.YunpianException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController  {
    @Resource
    private UserService userService;
    @Resource
    private HotstkService hotstkService;
    @Resource
    private QltytypeService qltytypeService;
    @Resource
    private AreaDao areaDao;
    @Resource
    private RedisUtils redisUtils;


    /**
     * 用户登录
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public String login( Model model, User user, HttpSession session){
        Map<String, User> userMap = UnRepeatLogin.getUserMap();
        if(userMap.size()>0) {
            Set<String> sessionIds = userMap.keySet();
            for (String sessionId : sessionIds) {
                if(userMap.get(sessionId).getNickname().equals(user.getNickname())){
                    model.addAttribute("AlertMessage","用户已登录");
                    return "login";
                }
            }
        }
           if (user != null && user.getNickname() != null && !"".equals(user.getNickname())) {
               User login = userService.login(user);
               if (login != null) {
                   UnRepeatLogin.setUserMap(session.getId(),login);
                   session.setAttribute("user", login);
                   model.addAttribute("AlertMessage", "登录成功");
                   return "redirect:/index";
               } else {
                   model.addAttribute("AlertMessage", "账号或密码错误");
                   return "login";
               }
           }

        return "login";
    }

    /**
     * 退出登陆
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String login(HttpSession session){
        session.invalidate();
        return "login";
    }

    /**
     * 显示用户信息
     * @param userId
     * @return
     */
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
    /**
     * 发短信验证码
     * @param session
     * @param user
     * @return
     * @throws YunpianException
     */
    @RequestMapping(value = "/registerVerify1",method = RequestMethod.POST)
    @ResponseBody
    public String register1(HttpSession session, User user) throws YunpianException {
        VoResponseJson vrj = new VoResponseJson();
        if(userService.querySelfUserByPhone(user)){
            //获取随机码长度
            String randomNumber = MessageUtil.getRandomNumber(4);
            try {
                //发送短信消息
                MessageUtil.singleSend(MessageUtil.getApiKey(), "【大唯科技】您的验证码是" + randomNumber, user.getMobile());
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

    @RequestMapping("/forgetPw")
    public String forgetPw(){
        return "forgetPw";
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
    /**
     * 用户注册
     * @param session
     * @param user
     * @param veriCode
     * @param model
     * @return
     */
    @RequestMapping(value = "/forgetPw",method =RequestMethod.POST)
    public String forgetPw(HttpSession session, User user, String veriCode, Model model){
        Object verifyCode = session.getAttribute("verifyCode");
        if(verifyCode!=null&&verifyCode.equals(veriCode)){
            if(userService.updateSelfUser(user)){
                return "login";
            }
        }
        return "regist";
    }


    /**
     * 跳转到后台用户管理页面
     * @param model
     * @return
     */
    @RequestMapping("/Xuser")
    public String Xuser(Model model){
        try {
            model.addAttribute("areaList",areaDao.queryAreas());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Xuser";
    }

    /**
     * 根据用户id查找公司页面
     * @param id
     * @param pager
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/company/{id}")
    public String showCompany(@PathVariable int id, Pager<Hotstk> pager, Model model) throws Exception{
        pager.getCondition().put("uid",id);
        model.addAttribute("page",hotstkService.queryHotstks(pager));
        model.addAttribute("qltytypeList",qltytypeService.queryQltytype());
        model.addAttribute("company",userService.queryUserById(id).getCompany());
        model.addAttribute("co",userService.queryUserById(id).getCo());
        model.addAttribute("addr",userService.queryUserById(id).getAddr());
        model.addAttribute("mobile",userService.queryUserById(id).getMobile());
        model.addAttribute("QQ",userService.queryUserById(id).getQq());
        model.addAttribute("contact",userService.queryUserById(id).getContact());
        model.addAttribute("tel",userService.queryUserById(id).getTel());
        model.addAttribute("wechat",userService.queryUserById(id).getWechat());
        model.addAttribute("website",userService.queryUserById(id).getWebsite());
        model.addAttribute("business",userService.queryUserById(id).getBusiness());
        model.addAttribute("profile",userService.queryUserById(id).getProfile());
        return "company";
    }


    /**
     * 下载全部型号信息
     * @param response
     */
    @RequestMapping("/downloadUser")
    public void downloadPn(HttpServletResponse response, User user){
        ExcelUtil.downExcelData(response,userService.downExcelByUser(user),"userList.xls");
    }

    /**
     * 上传信号信息
     * @param model
     * @param pmultipartfile
     * @return
     */
    @RequestMapping("/uploadUser")
    public String uploadMfg(Model model, MultipartFile pmultipartfile){
        if(pmultipartfile.isEmpty()){
            model.addAttribute("AlertMessage","上传用户信息失败");
        }else{
            if(userService.uploadUserList(pmultipartfile)) {
                model.addAttribute("AlertMessage", "上传用户信息成功");
            }
        }
        return "forward:/user/Xuser";
    }
}
