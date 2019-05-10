package com.uiqun.controller;

import com.uiqun.model.Rfq;
import com.uiqun.service.PntypeService;
import com.uiqun.service.QltytypeService;
import com.uiqun.service.RfqService;
import com.uiqun.utils.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class RfqController {
    @Resource
    private RfqService rfqService;
    @Resource
    private QltytypeService qltytypeService;
    @Resource
    private PntypeService pntypeService;

    @RequestMapping("/index")
    public String index(Model model, Pager<Rfq> pager, HttpSession session,
                        @RequestParam(required = false,defaultValue = "0")Integer pntype,
                        @RequestParam(required = false,defaultValue = "")String pn){
        if(pntype>0||!"".equals(pn)){
            pager.getCondition().put("pntype",pntype);
            pager.getCondition().put("pn",pn);
        }
        model.addAttribute("pntype",pntype);
        model.addAttribute("pn",pn);
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("pager",rfqService.queryRfqList(pager));
        model.addAttribute("qltytypeList",qltytypeService.queryQltytype());
        model.addAttribute("pntypeList",pntypeService.queryPntypes());
        return "index";
    }
    @RequestMapping("/inMfg/{rfqno}")
    public String inMfg(Model model, HttpSession session,Pager<Rfq> pager,@PathVariable("rfqno") String rfqno){
        model.addAttribute("user", session.getAttribute("user"));
        if(rfqno!=null&&!"".equals(rfqno)) {
            pager.getCondition().put("rfqno",rfqno);
            Pager<Rfq> rfqPager = rfqService.queryRfqList(pager);
            model.addAttribute("rfq", rfqPager.getDatas().get(0));
            return "quote";
        }
        return null;
    }

}
