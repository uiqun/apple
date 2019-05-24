package com.uiqun.controller;

import com.uiqun.model.Bomlist;
import com.uiqun.model.User;
import com.uiqun.service.*;
import com.uiqun.utils.ExcelUtil;
import com.uiqun.utils.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/bom")
public class BomController {
    @Resource
    private BomService bomService;
    @Resource
    private BomlistService bomlistService;
    @Resource
    private BtypeService btypeService;
    @Resource
    private PnService pnService;
    /**
     * 跳转到bom页面
     * @param model
     * @param pager
     * @param session
     * @return
     */
    @RequestMapping("/searchbom")
    public String searchbom(Model model, Pager<Bomlist> pager,HttpSession session){
        pager.getCondition().put("uid",((User)(session.getAttribute("user"))).getUid());
        model.addAttribute("btypeList",btypeService.queryBtypes());
        model.addAttribute("pager",bomlistService.queryBomlists(pager));
        return "BOM";
    }


    /**
     * BOM 上传
     * @param session
     * @param multipartFile
     * @param bomlist
     * @return
     */
    @RequestMapping("/uploadbom")
    public String uploadbom(HttpSession session, MultipartFile multipartFile, Bomlist bomlist){
        User user = (User)session.getAttribute("user");
        if(user!=null){
            bomlist.setCompany(user.getCompany());
            bomlist.setUid(user.getUid());
            try {
                bomlistService.insertBomlist(bomlist);
                int bomlistPrimaryKey = bomlist.getBid();
                org.springframework.core.io.Resource resource = multipartFile.getResource();
                InputStream inputStream = resource.getInputStream();
                List<List<Object>> boms = ExcelUtil.getBomListByExcel(inputStream,multipartFile.getOriginalFilename(),bomlistPrimaryKey,pnService);
                bomService.insertBoms(boms);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return "forward:/bom/searchbom";
    }





}
