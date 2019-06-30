package com.uiqun.controller;

import com.uiqun.service.HotstkService;
import com.uiqun.service.PntypeService;
import com.uiqun.service.RfqService;
import com.uiqun.utils.SessionCounter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class DataAnalysisController {
    @Resource
    private HotstkService hotstkService;
    @Resource
    private RfqService rfqService;
    @Resource
    private PntypeService pntypeService;

    @RequestMapping("/Xanalysis")
    public String Xuser(Model model){
        model.addAttribute("online", SessionCounter.getActiveSessions());
        return "Xanalysis";
    }


}
