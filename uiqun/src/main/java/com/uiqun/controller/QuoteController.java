package com.uiqun.controller;

import com.uiqun.model.Quote;
import com.uiqun.service.QuoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class QuoteController {
    @Resource
    private QuoteService quoteService;

    @RequestMapping("/addquote")
    public String addquote(Quote quote){
        if(quoteService.addQuote(quote)){

        }
        return null;
    }

    @RequestMapping("/jumpquote")
    public String jumpquote(){
        return "quote";
    }

}
