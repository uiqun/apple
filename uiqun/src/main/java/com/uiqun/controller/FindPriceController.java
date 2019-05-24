package com.uiqun.controller;

import com.uiqun.service.HotstkService;
import com.uiqun.service.PntypeService;
import com.uiqun.service.RfqService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FindPriceController {
    @Resource
    private HotstkService hotstkService;
    @Resource
    private RfqService rfqService;
    @Resource
    private PntypeService pntypeService;


    @RequestMapping("/findPrice")
    public String finPrice(Model model, HttpSession session,
                           @RequestParam(defaultValue = "0") int pntype,
                           @RequestParam(defaultValue = "")String pn){
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("pntype",pntype);
        condition.put("pn",pn);
        model.addAttribute("pntypeList",pntypeService.queryPntypes());
        model.addAttribute("condition",condition);
        model.addAttribute("rfqList",rfqService.queryRfqListFromFindPrice(condition));
        model.addAttribute("hotstkList",hotstkService.queryHotstksFromFindPrice(condition));
        return "findPrice";
    }

}
