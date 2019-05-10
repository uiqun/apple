package com.uiqun.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.uiqun.model.Mfg;
import com.uiqun.model.Pn;
import com.uiqun.model.Rfq;
import com.uiqun.service.*;
import com.uiqun.utils.Pager;
import com.uiqun.utils.VoResponseJson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RfqController {
    @Resource
    private RfqService rfqService;
    @Resource
    private QltytypeService qltytypeService;
    @Resource
    private PntypeService pntypeService;
    @Resource
    private MfgService mfgService;
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
    @RequestMapping(value = "/checkRfqPn",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String checkRfqPn(Pn pn){
        List<Mfg> mfgs = mfgService.checkRfqPn(pn);
        if(mfgs!=null){
            return JSONUtils.toJSONString(new VoResponseJson(mfgs));
        }
        return JSONUtils.toJSONString(new VoResponseJson("none",1111,"该型号不存在,请添加该型号"));
    }

}
