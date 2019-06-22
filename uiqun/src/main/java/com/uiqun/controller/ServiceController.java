package com.uiqun.controller;

import com.uiqun.model.Service;
import com.uiqun.service.ServiceToService;
import com.uiqun.utils.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class ServiceController {
    @Resource
    private ServiceToService serviceToService;



    @RequestMapping("/addService")
    public String addService(Model model, Service service){
        if(serviceToService.insertService(service)){
            model.addAttribute("AlertMessage", "添加服务成功");
        }else{
            model.addAttribute("AlertMessage", "添加服务失败");
        }
        return "Xservice";
    }

    @RequestMapping("/Xservice")
    public String findService(Model model, Pager<Service> pager,
                              @RequestParam(defaultValue = "") String sName,
                              @RequestParam(defaultValue = "") String uName){
        pager.getCondition().put("sName",sName);
        pager.getCondition().put("uName",uName);
        model.addAttribute("pager",serviceToService.queryService(pager));
        return "Xservice";
    }

    @RequestMapping("/deleteServiceById/{sid}")
    public String deleteServiceById(@PathVariable int sid, Model model){
        if(serviceToService.deleteServiceById(sid)){
            model.addAttribute("AlertMessage", "删除服务成功");
        }else{
            model.addAttribute("AlertMessage", "删除服务失败");
        }
        return "Xservice";
    }
}
