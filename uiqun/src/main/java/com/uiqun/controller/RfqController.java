package com.uiqun.controller;

import com.alibaba.fastjson.JSON;
import com.uiqun.model.Mfg;
import com.uiqun.model.Pn;
import com.uiqun.model.Rfq;
import com.uiqun.model.User;
import com.uiqun.service.MfgService;
import com.uiqun.service.PntypeService;
import com.uiqun.service.QltytypeService;
import com.uiqun.service.RfqService;
import com.uiqun.utils.Pager;
import com.uiqun.utils.VoResponseJson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    /**
     * 首页显示
     * @param model
     * @param pager
     * @param session
     * @param pntype
     * @param pn
     * @return
     */
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

    /**
     * 报价
     * @param model
     * @param session
     * @param pager
     * @param rfqno
     * @return
     */
    @RequestMapping("/inMfg/{rfqno}")
    public String inMfg(Model model, HttpSession session,Pager<Rfq> pager,@PathVariable("rfqno") String rfqno){
        model.addAttribute("user", session.getAttribute("user"));
        if(rfqno!=null&&!"".equals(rfqno)) {
            pager.getCondition().put("rfqno",rfqno);
            Pager<Rfq> rfqPager = rfqService.queryRfqList(pager);
            model.addAttribute("rfq", rfqPager.getDatas().get(0));
            model.addAttribute("qltyTypeList",qltytypeService.queryQltytype());
            return "quote";
        }
        return null;
    }
    @RequestMapping(value = "/checkRfqPn",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String checkRfqPn(Pn pn){
        List<Mfg> mfgs = mfgService.checkRfqPn(pn);
        if(mfgs!=null&&mfgs.size()>0){
            return JSON.toJSONString(new VoResponseJson(mfgs));
        }
        return JSON.toJSONString(new VoResponseJson("none",1111,"该型号不存在,请添加该型号"));
    }

    /**
     * 访问RFQ页面
     * @param model
     * @param pager
     * @param currentPage
     * @param request
     * @return
     */
    @RequestMapping("/jumprfq")
    public String jumpRfq(Model model, Pager<Rfq> pager,
                          @RequestParam(defaultValue = "0")int currentPage, HttpServletRequest request){
        if(pager.getCurrentPage()==0){
            pager.setCurrentPage(currentPage);
        }
        pager.setPageSize(10);
        User user = (User)request.getSession().getAttribute("user");
        pager.getCondition().put("uid",user.getUid());
        model.addAttribute("qltyTypeList",qltytypeService.queryQltytype());
        model.addAttribute("pntypeList",pntypeService.queryPntypes());
        model.addAttribute("pager",rfqService.queryRfqList(pager));
        return "rfq";
    }
    @RequestMapping("/addrfq")
    public String addrfq(Model model,Rfq rfq,HttpSession session){
        if(rfq.getMfg()==null) {
            model.addAttribute("AlertMessage", "添加询价失败,请填写规格型号后,查询该型号是否存在,再选择型号对应的品牌");
        }else{
            User user = (User)session.getAttribute("user");
            if(user==null){
                return "redirect:/user/login";
            }
            rfq.setCompany(user.getCompany());
            rfq.setUid(user.getUid());
            if(rfqService.addRfq(rfq)){
                model.addAttribute("AlertMessage", "添加询价成功");
            }
        }
        return "forward:/jumprfq";
    }

    @RequestMapping("/deleterfq")
    public String deleterfq(int rfqno){
        rfqService.deleterfq(rfqno);
        return "forward:/jumprfq";
    }
}
