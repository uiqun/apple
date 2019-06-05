package com.uiqun.controller;

import com.uiqun.model.Bomlist;
import com.uiqun.model.User;
import com.uiqun.service.BomService;
import com.uiqun.service.BomlistService;
import com.uiqun.service.BtypeService;
import com.uiqun.service.PnService;
import com.uiqun.utils.ExcelUtil;
import com.uiqun.utils.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/bomlist")
public class BomlistController {
    @Resource
    private BomlistService bomlistService;
    @Resource
    private BtypeService btypeService;
    @Resource
    private BomService bomService;
    @Resource
    private PnService pnService;

//@RequestMapping("/addBomlist")
//public String addBomlist(Model model,Bomlist bomlist) throws Exception{
//    model.addAttribute("btypeList",btypeService.queryBtypes());
//    bomlistService.insertBomlist(bomlist);
//    return "forward:/queryBomlists";
//}
//
//
//    @RequestMapping("/queryBomlists")
//   public String queryBomlists(Model model, Pager<Bomlist> pager, HttpSession session)throws Exception{
//       model.addAttribute("bomlists",bomlistService.queryBomlists(pager));
//        return "findPrice";
//    }




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
                InputStream inputStream = multipartFile.getInputStream();
                List<List<Object>> boms = ExcelUtil.getBomListByExcel(inputStream,multipartFile.getOriginalFilename(),bomlistPrimaryKey,pnService);
                bomService.insertBoms(boms);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return "forward:/bom/searchbom";
    }

    /**
     * 下载bomlist
     * @param bomid
     * @param bname
     * @param response
     */
    @RequestMapping("/downloadbom/{bomid}/{bname}")
    public void downloadbom(@PathVariable("bomid") int bomid, @PathVariable("bname") String bname
            , HttpServletResponse response){
        ExcelUtil.downExcelData(response,bomService.downloadbom(bomid,bname),"bomList.xls");
    }

    /**
     * 后台查看所有bomlist
     * @param model
     * @param pager
     * @return
     */
    @RequestMapping("/Xbom")
    public String Xbom(Model model, Pager<Bomlist> pager){
        model.addAttribute("pager",bomlistService.queryBomlists(pager));
        return "Xbom";
    }

    /**
     * 后台删除指定bomlist
     * @param bomid
     * @return
     */
    @RequestMapping("/deleteXbom/{bomid}")
    public String deleteXbom(@PathVariable("bomid") Integer bomid){
        bomlistService.deleteXbom(bomid);
        return  "redirect:/bomlist/Xbom";
    }

    /**
     * 上传修改
     * @param multipartFile
     * @param bomid
     * @return
     */
    @RequestMapping("/modifybom/{bomid}")
    public String modifybom(MultipartFile multipartFile
            ,@PathVariable("bomid") Integer bomid){
        bomlistService.modifybom(multipartFile,bomid,pnService);
        return  "redirect:/bomlist/Xbom";
    }

}
