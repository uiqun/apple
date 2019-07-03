package com.uiqun.controller;

import com.uiqun.model.Hotstk;
import com.uiqun.service.HotstkService;
import com.uiqun.service.PntypeService;
import com.uiqun.service.RfqService;
import com.uiqun.utils.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

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
    public String importExcelHotstk(@Param("uid") int uid, Model model, MultipartFile pmultipartfile){
        if(pmultipartfile.isEmpty()){
            model.addAttribute("AlertMessage","上传库存信息失败,请提交正确格式的库存清单");
        }else{
            if(hotstkService.uploadHotstkListByUid(uid,pmultipartfile)) {
                model.addAttribute("AlertMessage", "上传库存信息成功");
            }
        }
        return "hotStk";
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
