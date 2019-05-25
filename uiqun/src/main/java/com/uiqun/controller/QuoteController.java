package com.uiqun.controller;

import com.uiqun.model.Quote;
import com.uiqun.model.User;
import com.uiqun.service.QltytypeService;
import com.uiqun.service.QuoteService;
import com.uiqun.service.RfqService;
import com.uiqun.utils.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class QuoteController {
    @Resource
    private QuoteService quoteService;
    @Resource
    private RfqService rfqService;
    @Resource
    private QltytypeService qltytypeService;

    /**
     * 添加报价
     * @param quote
     * @return
     */
    @RequestMapping("/addquote")
    public String addquote(Quote quote,HttpSession session){
        User user = (User)session.getAttribute("user");
        quote.setCompany(user.getCompany());
        quote.setUid(user.getUid());
        if(quoteService.addQuote(quote)){
            return "redirect:/inquote/"+quote.getRfqno();
        }
        return null;
    }

    /**
     * 报价
     * @param model
     * @param session
     * @param pager
     * @param rfqno
     * @return
     */
    @RequestMapping("/inquote/{rfqno}")
    public String inMfg(Model model, HttpSession session, Pager<Quote> pager, @PathVariable("rfqno") int rfqno){
        pager.getCondition().put("user",session.getAttribute("user"));
        pager.getCondition().put("rfqno",rfqno);
        if(rfqno>0) {
            model.addAttribute("rfq", rfqService.queryRfq(rfqno));
            model.addAttribute("qltyTypeList",qltytypeService.queryQltytype());
            model.addAttribute("pager",quoteService.queryQuoteByRfq(pager));
            return "quote";
        }
        return null;
    }

    @RequestMapping("/inquote1")
    public String inMfg1(Model model, HttpSession session, Pager<Quote> pager){
        pager.getCondition().put("user",session.getAttribute("user"));
            model.addAttribute("rfq", null);
        pager.getCondition().put("rfqno","");
            model.addAttribute("qltyTypeList",qltytypeService.queryQltytype());
            model.addAttribute("pager",quoteService.queryQuoteByRfq(pager));
            return "quote";
    }
}
