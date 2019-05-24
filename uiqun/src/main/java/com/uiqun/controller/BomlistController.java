package com.uiqun.controller;

import com.uiqun.model.Bomlist;
import com.uiqun.service.BomlistService;
import com.uiqun.service.BtypeService;
import com.uiqun.utils.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class BomlistController {
@Resource
    private BomlistService bomlistService;
@Resource
    private BtypeService btypeService;

@RequestMapping("/addBomlist")
public String addBomlist(Model model,Bomlist bomlist) throws Exception{
    model.addAttribute("btypeList",btypeService.queryBtypes());
    bomlistService.insertBomlist(bomlist);
    return "queryBomlists";
}


    @RequestMapping("/queryBomlists")
   public String queryBomlists(Model model, Pager<Bomlist> pager, HttpSession session)throws Exception{
       model.addAttribute("bomlists",bomlistService.queryBomlists(pager));
        return "findPrice";
    }
}
