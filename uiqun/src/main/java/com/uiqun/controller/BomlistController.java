package com.uiqun.controller;

import com.uiqun.model.Bomlist;
import com.uiqun.model.User;
import com.uiqun.service.BomService;
import com.uiqun.service.BomlistService;
import com.uiqun.service.BtypeService;
import com.uiqun.service.PnService;
import com.uiqun.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    @RequestMapping("/downloadbom/{bomid}/{bname}")
    public void downloadbom(@PathVariable("bomid") int bomid, @PathVariable("bname") String bname
            , HttpServletResponse response){
        Workbook downloadbom = bomService.downloadbom(bomid,bname);
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + "bomMessage.xls");
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            downloadbom.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
