package com.uiqun.service.impl;

import com.uiqun.dao.AreaDao;
import com.uiqun.dao.UserDao;
import com.uiqun.model.Area;
import com.uiqun.model.User;
import com.uiqun.model.Vendors;
import com.uiqun.service.UserService;
import com.uiqun.utils.DateUtil;
import com.uiqun.utils.ExcelUtil;
import com.uiqun.utils.UpLoadUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userdao;
    @Resource
    private DateUtil dateUtil;
    /**
     * 登录
     * @param user
     * @return
     */
    @Resource
    private AreaDao areaDao;


    public User login(User user) {
        try {
            User user1 = userdao.queryUser(user);
            if(user1!=null) {
                //设置最后登录时间
                user1.setLastLogin(new Date());
                userdao.modifyLastLoginDate(user1);
            }
            return user1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Vendors> queryUtypesById(int utypeid) {
        try {
            return userdao.queryUtypesById(utypeid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User queryUserById(int userId) {
        try {
            return userdao.queryUserById(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean register(User user) {
        try {
            return userdao.insertUser(user)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User queryUserDetail(User user){
        try {
            return userdao.queryUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        return userdao.updateUser(user)>0;
    }

    @Override
    public boolean updateSelfUser(User user) {
        try {
            return userdao.updateSelfUser(user)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveUser(User user) {
        return userdao.saveUser(user)>0;
    }
    @Override
    public boolean queryUserByPhone(String moblie) {
        try {
            return userdao.queryUserByPhone(moblie)==null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean querySelfUserByPhone(User user) {
        try {
            return userdao.querySelfUserByPhone(user)!=null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Area> queryAreas() {
        try {
            return areaDao.queryAreas();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Workbook downExcelByUser(User user) {
        List<User> users = userdao.queryUsersByAdmin(user);
        //2003版本的
        Workbook wk=new HSSFWorkbook();
        Sheet bomList = wk.createSheet("用户列表");
        bomList.setDefaultColumnWidth(20);
        for (int i = 0; i < users.size()+1; i++) {
            Row row = bomList.createRow(i);
            if(i==0){
                row.createCell(0).setCellValue("用户编号");
                row.createCell(1).setCellValue("用户名");
                row.createCell(2).setCellValue("手机");
                row.createCell(3).setCellValue("密码");
                row.createCell(4).setCellValue("公司名称");
                row.createCell(5).setCellValue("公司简称");
                row.createCell(6).setCellValue("区域");
                row.createCell(7).setCellValue("用户类型");
                row.createCell(8).setCellValue("联系人");
                row.createCell(9).setCellValue("职位");
                row.createCell(10).setCellValue("电话");
                row.createCell(11).setCellValue("QQ");
                row.createCell(12).setCellValue("微信");
                row.createCell(13).setCellValue("爱好");
                row.createCell(14).setCellValue("邮箱");
                row.createCell(15).setCellValue("地址");
                row.createCell(16).setCellValue("网址");
                row.createCell(17).setCellValue("站内网址");
                row.createCell(18).setCellValue("主营业务");
                row.createCell(19).setCellValue("公司简介");
                row.createCell(20).setCellValue("公司商标");
                row.createCell(21).setCellValue("宣传图片");
                row.createCell(22).setCellValue("营业执照号码");
                row.createCell(23).setCellValue("营业执照");
                row.createCell(24).setCellValue("是否通过认证");
                row.createCell(25).setCellValue("询价权限");
                row.createCell(26).setCellValue("报价权限");
                row.createCell(27).setCellValue("推荐库存权限");
                row.createCell(28).setCellValue("查询权限");
                row.createCell(29).setCellValue("供应商权限");
                row.createCell(30).setCellValue("BOM权限");
                row.createCell(31).setCellValue("最后登录时间");
                continue;
            }
            row.createCell(0).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getUid()));
            row.createCell(1).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getNickname()));
            row.createCell(2).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getMobile()));
            row.createCell(3).setCellValue(ExcelUtil.setResponseValue("权限不足，不可查看或修改"));
            row.createCell(4).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getCompany()));
            row.createCell(5).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getCo()));
            row.createCell(6).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getArea()));
            row.createCell(7).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getUtype()));
            row.createCell(8).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getContact()));
            row.createCell(9).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getTitle()));
            row.createCell(10).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getTel()));
            row.createCell(11).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getQq()));
            row.createCell(12).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getWechat()));
            row.createCell(13).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getHobby()));
            row.createCell(14).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getEmail()));
            row.createCell(15).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getAddr()));
            row.createCell(16).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getWebsite()));
            row.createCell(17).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getWebsite1()));
            row.createCell(18).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getBusiness()));
            row.createCell(19).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getProfile()));
            row.createCell(20).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getUlogo()));
            row.createCell(21).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getImage()));
            row.createCell(22).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getCertid()));
            row.createCell(23).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getCert()));
            row.createCell(24).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getIscert()));
            row.createCell(25).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getRrfq()));
            row.createCell(26).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getRquote()));
            row.createCell(27).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getRhot()));
            row.createCell(28).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getRfind()));
            row.createCell(29).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getRvendor()));
            row.createCell(30).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getRbom()));
            row.createCell(31).setCellValue(ExcelUtil.setResponseValue(users.get(i-1).getLastLogin()));
        }
        return  wk;
    }

    @Override
    public boolean uploadUserList(MultipartFile pmultipartfile) {
        try {
            //读取进来的列表
            List<List<Object>> uploadListByExcel = ExcelUtil.getUploadListByExcel(pmultipartfile.getInputStream(),
                    pmultipartfile.getOriginalFilename());
            List<User> users = UpLoadUtil.tranceObject(uploadListByExcel, User.class);
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                if(user.getUid()==null||user.getUid()==0){
                    userdao.insertOneUserByAdmin(user);
                }else {
                    userdao.modifyUserByAdmin(user);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
