package com.uiqun.controller;

import com.uiqun.model.Hotstk;
import com.uiqun.model.User;
import com.uiqun.service.HotstkService;
import com.uiqun.service.PntypeService;
import com.uiqun.service.RfqService;
import com.uiqun.utils.ExcelUtil;
import com.uiqun.utils.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class HotstkController {
    @Resource
    private HotstkService hotstkService;
    @Resource
    private RfqService rfqService;
    @Resource
    private PntypeService pntypeService;

    /**
     * 上传信号信息
     * @param model
     * @param pmultipartfile
     * @return
     */
    @RequestMapping("/uploadHotstk")
    public String importExcelHotstk(HttpSession session, Model model, MultipartFile pmultipartfile){
        User user =(User) session.getAttribute("user");
        //判断是否有权限上传热卖库存
        if(user!=null&&user.getRhot()>0){
            if(pmultipartfile.isEmpty()){
                model.addAttribute("AlertMessage","上传库存信息失败,请提交正确格式的库存清单");
            }else{
                if(hotstkService.uploadHotstkListByUid(user,pmultipartfile)) {
                    model.addAttribute("AlertMessage", "上传库存信息成功");
                }else{
                    model.addAttribute("AlertMessage","上传库存信息失败,提交的热卖信息超过500条或文件中格式错误。");
                }
            }
        }else {
            model.addAttribute("AlertMessage","权限不足,上传库存信息失败,请联系客服开通权限");
        }
        return "forward:/queryHotstks/"+user.getUid();
    }

    /**
     * 下载热卖库存模板
     * @param response
     */
    @RequestMapping("/downloadHotstkSample")
    public void downloadHotstk(HttpServletResponse response){
        String[] header={"型号","型号类型","品牌","封装","数量","单价","批次","质量标准","货期","购买链接"};
        //模板创建模板并下载
        ExcelUtil.downTemplate(response,header,"热卖库存信息","hotskList.xls");
    }


    /**
     *查询热门库存
     * @param uid
     * @param pager
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/queryHotstks/{uid}")
    public String showList(@PathVariable("uid")int uid, Pager<Hotstk> pager, Model model) throws Exception{
        pager.getCondition().put("uid",uid);
        model.addAttribute("page",hotstkService.queryHotstks(pager));
        return "hotStk";
    }

    /**
     * 后台库存管理页面
     * @return
     */
    @RequestMapping("/Xhotstk")
    public String Xhotstk(Pager<Hotstk> pager,Hotstk hotstk, Model model){
        pager.getCondition().put("hotstk",hotstk);
        model.addAttribute("pager",hotstkService.queryHotstks(pager));
        return "Xhotstk";
    }

    /**
     * 后台删除热卖
     * @param hotid
     * @return
     */
    @RequestMapping("/deletXhotstk/{hotid}")
    public String deletXhotstk(@PathVariable("hotid") int hotid){
        hotstkService.deletXhotstk(hotid);
        return "redirect:/Xhotstk";
    }


}
