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

    @RequestMapping("/queyrPlatinfoById{id}")
    public String queyrPlatinfoById(int id, Model model){
      model.addAttribute("Platinfo", platinfoService.queryPlatinfoById(id));
        return "platinfo";
    }

    @RequestMapping("/updatePlatinfo")
    public String updatePlatinfo(Platinfo platinfo,Model model){
        if (platinfoService.updatePlateinfos(platinfo)) {
            model.addAttribute("AlertMessage", "平台信息修改成功");
        }
        model.addAttribute("AlertMessage", "平台信息修改失败");
        return "Xaboutus";
    }

}
