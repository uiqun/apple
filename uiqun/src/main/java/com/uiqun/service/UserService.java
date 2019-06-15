package com.uiqun.service;

import com.uiqun.model.Area;
import com.uiqun.model.User;
import com.uiqun.model.Vendors;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    User login(User user);
    /**
     * 按照分类id查询子分类
     * @param utypeId
     * @return
     * @throws Exception
     */
    List<Vendors> queryUtypesById(int utypeId);
    /**
     * 查询用户
     * @param userId
     * @return
     * @throws Exception
     */
    User queryUserById(int userId);

    /**
     * 用户注册
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     * 查询用户详情
     * @param user
     * @return
     */
    User queryUserDetail(User user);

    /**
     * 更新用户数据
     * @param user
     * @return
     */
    boolean updateUser(User user);

    /**
     * 保存用户数据
     * @param user
     * @return
     */
    boolean saveUser(User user);

    /**
     * 根据用户电话查询用户
     * @param moblie
     * @return
     */
    boolean queryUserByPhone(String moblie);

    /**
     * 查询区域
     * @return
     */
    List<Area> queryAreas();

    /**
     * 获取下载的excel文件
     * @param user
     * @return
     */
    Workbook downExcelByUser(User user);

    /**
     * 根据excel批量上传用户信息
     * @param pmultipartfile
     * @return
     */
    boolean uploadUserList(MultipartFile pmultipartfile);
}
