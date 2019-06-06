package com.uiqun.controller;

import com.uiqun.model.Advs;
import com.uiqun.service.AdvsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class AdvsController {
    @Resource
    private AdvsService advsService;

    @RequestMapping("/Xadvs")
    public String Xadvs(Model model){
        model.addAttribute("title",advsService.queryAdvs().getTitle());
        model.addAttribute("content1",advsService.queryAdvs().getContent1());
        model.addAttribute("content2",advsService.queryAdvs().getContent2());
        model.addAttribute("content3",advsService.queryAdvs().getContent3());
        model.addAttribute("content4",advsService.queryAdvs().getContent4());
        model.addAttribute("content5",advsService.queryAdvs().getContent5());
        model.addAttribute("content6",advsService.queryAdvs().getContent6());
        model.addAttribute("content7",advsService.queryAdvs().getContent7());
        model.addAttribute("content8",advsService.queryAdvs().getContent8());
        model.addAttribute("content9",advsService.queryAdvs().getContent9());
        model.addAttribute("content10",advsService.queryAdvs().getContent10());
        return "Xadvs";
    }

    @RequestMapping("/updateAdvs")
    public String updateAdvs(Advs advs,Model model){
    if(advsService.updateAdvs(advs)){
        model.addAttribute("AlertMessage","更新广告词成功！");
    }else{
        model.addAttribute("AlertMessage","更新广告词失败！");
    }
        return "redirect:/Xadvs";
    }

}
