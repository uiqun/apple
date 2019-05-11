package com.uiqun.controller;

import com.uiqun.model.Utype;
import com.uiqun.service.UtypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UtypeController {
    @Resource
    UtypeService utypeService;


    @RequestMapping("/queryUtypes")
    public String queryUtypes(Model model,@RequestParam(defaultValue = "0") int parentId){
                   List<Utype> utypes = utypeService.queryUtypes(parentId);
            model.addAttribute("utypes",utypes);

        return "vendor";
    }

    @RequestMapping("/queryLevel/{id}")
    public String queryLevel(Model model, @PathVariable("id")int id){
        List<Utype> utypes = utypeService.queryUtypes(id);
        model.addAttribute("LevelList",utypes);
        return "forward:/queryUtypes";
    }
}
