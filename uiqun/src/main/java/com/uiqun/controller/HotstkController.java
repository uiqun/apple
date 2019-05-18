package com.uiqun.controller;

import com.uiqun.model.Hotstk;
import com.uiqun.service.HotstkService;
import com.uiqun.service.PntypeService;
import com.uiqun.service.RfqService;
import com.uiqun.utils.Encrypt_Dncrypt;
import com.uiqun.utils.ExcelUtil;
import com.uiqun.utils.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HotstkController {
    @Resource
    private HotstkService hotstkService;
    @Resource
    private RfqService rfqService;
    @Resource
    private PntypeService pntypeService;

    @RequestMapping("/uploadHotstk")
    public String importExcelHotstk(Model model, MultipartFile upload, HttpSession session) throws Exception {
        boolean flag =false;
        String filename = null;
        String upfilelogin =null;
        if(upload.getName()!=null&&!"".equals(upload.getName())){
            //图片上传
            //获取用户上传的Logo的文件名
            filename = Encrypt_Dncrypt.getUpLoadFileName(session,upload,"uploadHotstk");
            try {
                upfilelogin = session.getServletContext().getRealPath("upfilelogin");
                //保存路径&保存文件名
                upload.transferTo(new File("C:\\Users\\Administrator\\Desktop\\bookinfo - 副本\\target\\classes\\static\\upfilelogin",filename));
                flag = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(flag){
            if(filename==null||upfilelogin==null){
                model.addAttribute("AlertMessage","添加型号失败");
                return "forward:/addHotstkPage";
            }else{
                upfilelogin+="/"+filename;
            }
            String filepath = upfilelogin;
            FileInputStream inputStream = new FileInputStream(new File(filepath));
            List<List<Object>> list = ExcelUtil.getBankListByExcel(inputStream, filepath);
            //添加到数据库
            hotstkService.insertHotstks(list);
            //删除以添加到数据库中的文件
            File file = new File(filepath);
            if(file.exists()){
                file.delete();
            }
            //返回消息
            model.addAttribute("AlertMessage","添加型号成功");
        }else{
            model.addAttribute("AlertMessage","添加型号失败");
        }
        return "forward:/addHotstkPage";
    }


    @RequestMapping("/queryHotstks")
    public String showList(int uid, Pager<Hotstk> pager, Model model) throws Exception{
        pager.getCondition().put("uid",uid);
        model.addAttribute("page",hotstkService.queryHotstks(pager));
        return "hotStk";
    }

    @RequestMapping("/findPrice")
    public String finPrice(Model model, HttpSession session,
                           @RequestParam(defaultValue = "0") int pntype,
                           @RequestParam(defaultValue = "")String pn){
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("pntype",pntype);
        condition.put("pn",pn);
        model.addAttribute("pntypeList",pntypeService.queryPntypes());
        model.addAttribute("condition",condition);
        model.addAttribute("rfqList",rfqService.queryRfqListFromFindPrice(condition));
        model.addAttribute("hotstkList",hotstkService.queryHotstksFromFindPrice(condition));
        return "findPrice";
    }

}