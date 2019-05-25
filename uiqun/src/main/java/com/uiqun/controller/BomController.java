package com.uiqun.controller;

import com.uiqun.model.Bomlist;
import com.uiqun.model.User;
import com.uiqun.service.BomlistService;
import com.uiqun.service.BtypeService;
import com.uiqun.utils.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/bom")
public class BomController {

    @Resource
    private BomlistService bomlistService;
    @Resource
    private BtypeService btypeService;


    /**
     * 跳转到bom页面
     * @param model
     * @param pager
     * @param session
     * @return
     */
    @RequestMapping("/searchbom")
    public String searchbom(Model model, Pager<Bomlist> pager, HttpSession session){
        pager.getCondition().put("uid",((User)(session.getAttribute("user"))).getUid());
        model.addAttribute("btypeList",btypeService.queryBtypes());
        model.addAttribute("pager",bomlistService.queryBomlists(pager));
        return "BOM";
    }


    @RequestMapping("/querybom")
    public String querybom(Model model, HttpSession session,Bomlist bomlist){
        bomlist.setUid( ((User)(session.getAttribute("user"))).getUid() );
        model.addAttribute("bomam",bomlistService.getBomlist(bomlist));
        return "forward:/bom/searchbom";
    }

    @RequestMapping("/modifybom")
    public String modifybom(HttpSession session,Bomlist bomlist){
        bomlist.setUid( ((User)(session.getAttribute("user"))).getUid() );
        if(bomlistService.modifyBomList(bomlist)){
            return  "redirect:/bom/searchbom";
        }
        return  null;
    }

}
