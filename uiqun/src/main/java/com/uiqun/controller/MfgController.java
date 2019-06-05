package com.uiqun.controller;

import com.uiqun.model.Mfg;
import com.uiqun.service.MfgService;
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
import java.io.IOException;

@Controller
public class MfgController {
    @Resource
    private MfgService mfgService;
    //跳转网页
    @RequestMapping("/insertmfg")
    public String mfg(){
        return "addMfg";
    }
    //新增品牌
    @RequestMapping("/addmfg")
    public String addMfg(Model model, Mfg mfg, MultipartFile upload, HttpSession session){
        boolean flag =false;
        if(!upload.isEmpty()){
            //图片上传
            //获取用户上传的Logo的文件名
            String filename = Encrypt_Dncrypt.getUpLoadFileName(session,upload,"logo");
            mfg.setMlogo(filename);
            try {
                String upfilelogin = ResourceUtils.getURL("classpath:static/upfilelogin").getPath();
                //保存路径&保存文件名
                upload.transferTo(new File(upfilelogin,filename));
                flag=true;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(mfg.getMfgName()==null&&"".equals(mfg.getMfgName())){
            model.addAttribute("AlertMessage","添加品牌失败,必须填写中文或者英文");
        }else{
            flag=true;
        }
        if(flag){
            //添加到数据库
            mfgService.addMfg(mfg);
            //返回消息
            model.addAttribute("AlertMessage","添加品牌成功");
        }
        return "forward:/insertmfg";
    }

    //检测品牌
    @RequestMapping(value = "/checkMfg",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String checkMfg(Mfg mfg){
        if(mfgService.checkMfg(mfg)){
            return "{\"success\":\"no\"}";
        }else {
            return "{\"success\":\"yes\"}";
        }
    }


    @RequestMapping("/Xmfg")
    public String Xmfg(){
        return "Xmfg";
    }

    @RequestMapping("/queryMfg")
    public String queryMfg(Model model,Mfg mfg){
        model.addAttribute("mfg",mfgService.getMfg(mfg));
        return "Xmfg";
    }

    @RequestMapping("/queryOneMfg")
    public String queryOneMfg(Model model,Mfg mfg){
        model.addAttribute("mfg",mfgService.getMfg(mfg));
        return "Xmfg";
    }

    /**
     * 后台更新品牌信息
     * @param model
     * @param mfg
     * @param mfgmultipartFile
     * @param session
     * @return
     */
    @RequestMapping("/toUpdateMfg")
    public String toUpdateMfg(Model model,Mfg mfg,MultipartFile mfgmultipartFile,HttpSession session){
        boolean flag =false;
        if(!mfgmultipartFile.isEmpty()){
            //图片上传
            //获取用户上传的Logo的文件名
            String filename = Encrypt_Dncrypt.getUpLoadFileName(session,mfgmultipartFile,"mfgmultipartFile");
            mfg.setMlogo(filename);
            try {
                String upfilelogin = ResourceUtils.getURL("classpath:static/upfilelogin").getPath();
                //保存路径&保存文件名
                mfgmultipartFile.transferTo(new File(upfilelogin,filename));
                flag=true;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(mfg.getMfgName()==null&&"".equals(mfg.getMfgName())){
            model.addAttribute("AlertMessage","修改品牌失败,必须填写中文或者英文");
        }else{
            flag=true;
        }
        if(flag){
            //添加到数据库
            mfgService.modifyMfg(mfg);
            //返回消息
            model.addAttribute("AlertMessage","修改品牌成功");
        }
        return "Xmfg";
    }

    /**
     * 下载全部品牌信息
     * @param response
     */
    @RequestMapping("/downloadMfg")
    public void downloadMfg(HttpServletResponse response){
        ExcelUtil.downExcelData(response,mfgService.getMfgExcel(),"mfgList.xls");
    }

    /**
     * 上传信息
     * @param model
     * @param smultipartfile
     * @return
     */
    @RequestMapping("/uploadMfg")
    public String uploadMfg(Model model,MultipartFile smultipartfile){
        if(smultipartfile.isEmpty()){
            model.addAttribute("AlertMessage","上传品牌失败,请提交正确格式的品牌信息");
        }else{
            if(mfgService.uploadMfgList(smultipartfile)) {
                model.addAttribute("AlertMessage", "上传品牌成功");
            }
        }
        return "Xmfg";
    }
}
