package com.uiqun.controller;

import com.alibaba.fastjson.JSON;
import com.uiqun.model.*;
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
    @Resource
    private QuoteService quoteService;
    @Resource
    private AdvsService advsService;


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
        pager.getCondition().put("pntype",pntype);
        pager.getCondition().put("pn",pn);
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("pager",rfqService.queryRfqList(pager));
        model.addAttribute("qltytypeList",qltytypeService.queryQltytype());
        model.addAttribute("pntypeList",pntypeService.queryPntypes());
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
        return "index";
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

    @RequestMapping(value = "/checkRfqPnX",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String checkRfqPnX(Pn pn){
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
     * @param request
     * @return
     */
    @RequestMapping("/jumprfq")
    public String jumpRfq(Model model, Pager<Rfq> pager,
                           HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        pager.getCondition().put("uid",user.getUid());
        model.addAttribute("qltyTypeList",qltytypeService.queryQltytype());
        model.addAttribute("pntypeList",pntypeService.queryPntypes());
        model.addAttribute("pager",rfqService.queryRfqList(pager));
        return "rfq";
    }

    /**
     * 添加询价
     * @param model
     * @param rfq
     * @param request
     * @return
     */
    @RequestMapping("/addrfq")
    public String addrfq(Model model,Rfq rfq,HttpServletRequest request){
        HttpSession session =request.getSession();
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
                //添加询价次数

                model.addAttribute("AlertMessage", "添加询价成功");
            }
        }
        return "redirect:/jumprfq";
    }

    /**
     * 查看明细
     * @param model
     * @param rfqno
     * @param session
     * @return
     */
    @RequestMapping("/checkRfqDetail/{rfqno}")
    public String checkRfqDetail(Model model,Pager<Quote> pager,
                                 @PathVariable("rfqno")int rfqno, HttpSession session){
        pager.getCondition().put("rfqno",rfqno);
        pager.getCondition().put("uid",((User)session.getAttribute("user")).getUid());
        model.addAttribute("pager",quoteService.queryQuotesByRfq(pager));
        model.addAttribute("rfq",rfqService.queryRfq(rfqno));
        return "rfqDetail";
    }

    /**
     * 后台询价管理页面
     * @return
     */
    @RequestMapping("/Xrfq")
    public String Xrfq(Pager<Rfq> pager, Rfq rfq, Model model){
        pager.getCondition().put("rfq",rfq);
        model.addAttribute("pager",rfqService.queryRfqByAdmin(pager));
        return "Xrfq";
    }

    /**
     * 后台删除询价
     * @param rfqid
     * @return
     */
    @RequestMapping("/deletXrfq/{rfqid}")
    public String deletXrfq(@PathVariable("rfqid") int rfqid){
        rfqService.deletXrfqByAdmin(rfqid);
        return "redirect:/Xrfq";
    }
}
