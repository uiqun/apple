package com.uiqun.controller;

import com.uiqun.model.Mfg;
import com.uiqun.model.Pn;
import com.uiqun.service.MfgService;
import com.uiqun.service.PnService;
import com.uiqun.service.PntypeService;
import com.uiqun.utils.Encrypt_Dncrypt;
import com.uiqun.utils.ExcelUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class PnController {
    @Resource
    private PnService pnService;
    @Resource
    private PntypeService pntypeService;
    @Resource
    private MfgService mfgService;
    /**
     * Excel导入型号
     * @param model
     * @param upload
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/importExcel")
    public String importExcelPnList(Model model, MultipartFile upload, HttpSession session) throws Exception {
        boolean flag =false;
        String filename = null;
        String upfilelogin =null;
        if(!upload.isEmpty()){
            //图片上传
            //获取用户上传的Logo的文件名
            filename = Encrypt_Dncrypt.getUpLoadFileName(session,upload,"importPnTypeExcel");
            try {
                upfilelogin = session.getServletContext().getRealPath("upfilelogin");
                //保存路径&保存文件名
                upload.transferTo(new File(upfilelogin,filename));
                flag = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(flag){
            if(filename==null||upfilelogin==null){
                model.addAttribute("AlertMessage","添加型号失败");
                return "forward:/addPnsPage";
            }else{
                upfilelogin+="/"+filename;
            }
            String filepath = upfilelogin;
            File file = new File(filepath);
            FileInputStream inputStream = new FileInputStream(file);
            List<List<Object>> list = ExcelUtil.getUploadListByExcel(inputStream, filename);
            //添加到数据库
            pnService.insertPns(list);
            //删除以添加到数据库中的文件
            if(file.exists()){
                file.delete();
            }
            //返回消息
            model.addAttribute("AlertMessage","添加型号成功");
        }else{
            model.addAttribute("AlertMessage","添加型号失败");
        }
        return "forward:/addPnsPage";
    }

    /**
     * 页面跳转到Xpn
     * @return
     */
    @RequestMapping("/addPnsPage")
    public String addPnsPage(){
        return "Xpn";
    }

    /**
     * 页面跳转/型号添加
     * @param model
     * @param upload
     * @param pn
     * @return
     */
    @RequestMapping("/addpn")
    public String addPn(HttpSession session,Model model,MultipartFile upload,Pn pn){
        model.addAttribute("pntypeList",pntypeService.queryPntypes());
        if(pn.getPn()!=null){
            if(pnService.addPn(pn)) {
                if(!upload.isEmpty()){
                    //图片上传
                    //获取用户上传的Logo的文件名
                    String filename = Encrypt_Dncrypt.getUpLoadFileName(session,upload,"addpn");
                    try {
                        String upfilelogin = ResourceUtils.getURL("classpath:static/upfilelogin").getPath();
                        //保存路径&保存文件名
                        upload.transferTo(new File(upfilelogin,filename));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                model.addAttribute("AlertMessage", "添加型号成功");
            }else{
                model.addAttribute("AlertMessage", "添加型号失败");
            }
        }
        return "addPn";
    }
    @RequestMapping(value="/checkPn",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String checkPn(Pn pn){
        Mfg mfg = new Mfg();
        mfg.setMfgName(pn.getMfg());
        //检查品牌是否存在
        if(mfgService.checkMfg(mfg)) {
            //检查型号是否存在
            if (pnService.checkPn(pn)) {
                return "{\"success\":\"no\"}";
            } else {
                return "{\"success\":\"yes\"}";
            }
        }else{
            return "{\"success\":\"none\"}";
        }
    }


    @RequestMapping("/Xpn")
    public String Xpn(Model model){
        model.addAttribute("pntypeList",pntypeService.queryPntypes());
        return "Xpn";
    }




    @RequestMapping("/queryOnePn")
    public String queryOnePn(Model model,Pn pn){
        model.addAttribute("pn",pnService.getPnByAdmin(pn));
        return "forward:/Xpn";
    }

    /**
     * 后台更新型号信息
     * @param model
     * @param pn
     * @return
     */
    @RequestMapping("/toUpdatePn")
    public String toUpdatePn(Model model,Pn pn){
        if(pnService.modifyPnByAdmin(pn)){
            //返回消息
            model.addAttribute("AlertMessage","修改型号信息成功");
        }else{
            model.addAttribute("AlertMessage","修改型号信息失败");
        }
        model.addAttribute("pn",pn);
        return "forward:/queryOnePn";
    }

    /**
     * 下载全部型号信息
     * @param response
     */
    @RequestMapping("/downloadPn")
    public void downloadPn(HttpServletResponse response,Pn pn){
        ExcelUtil.downExcelData(response,pnService.downExcelByPn(pn),"pnList.xls");
    }

    /**
     * 上传信号信息
     * @param model
     * @param pmultipartfile
     * @return
     */
    @RequestMapping("/importPnExcleByAdmin")
    public String uploadMfg(Model model,MultipartFile pmultipartfile){
        if(pmultipartfile.isEmpty()){
            model.addAttribute("AlertMessage","上传型号信息失败,请提交正确格式的型号信息");
        }else{
            if(pnService.uploadPnList(pmultipartfile)) {
                model.addAttribute("AlertMessage", "上传型号信息成功");
            }
        }
        return "Xmfg";
    }
}
