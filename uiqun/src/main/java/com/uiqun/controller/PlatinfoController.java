package com.uiqun.controller;

import com.uiqun.model.Platinfo;
import com.uiqun.service.PlatinfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class PlatinfoController{
    @Resource
    private PlatinfoService platinfoService;

    @RequestMapping("/queryPlatinfo")
    public String queryPlatinfo(Model model){
        model.addAttribute("Platinfo", platinfoService.queryPlatinfo());
        return "platInfo";
    }

    @RequestMapping("/queryAboutus")
    public String queryAboutus(Model model){
        model.addAttribute("Platinfo", platinfoService.queryPlatinfo());
        return "Xaboutus";
    }
    @RequestMapping("/updatePlatinfo")
    public String updatePlatinfo( Platinfo platinfo, Model model){
        if (platinfoService.updatePlatinfo(platinfo)) {
            model.addAttribute("AlertMessage", "平台信息修改成功");
        }
        model.addAttribute("AlertMessage", "平台信息修改失败");
        return "redirect:/queryAboutus";
    }
}
