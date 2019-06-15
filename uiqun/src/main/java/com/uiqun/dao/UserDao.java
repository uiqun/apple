package com.uiqun.dao;

import com.uiqun.model.User;
import com.uiqun.model.Vendors;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    /**
     * 查询用户
     * @param user
     * @return
     * @throws Exception
     */
    User queryUser(User user)throws Exception;

    /**
     * 查询用户
     * @param userId
     * @return
     * @throws Exception
     */
    User queryUserById(int userId)throws Exception;

    /**
     * 按照分类查询所有供应商
     * @param utypeId
     * @return
     */
    List<User> queryVendor(int utypeId)throws Exception;

    /**
     * 按照分类id查询子分类
     * @param utypeId
     * @return
     * @throws Exception
     */
    List<Vendors> queryUtypesById(int utypeId)throws Exception;

    /**
     * 添加用户
     * @param user
     * @return
     */
    int insertUser(User user);

    int updateUser(User user);

    int saveUser(User user);

    User queryUserByPhone(@Param("mobile") String mobile);

    /**
     * 修改最后登录时间
     * @param user
     * @return
     */
    int modifyLastLoginDate(User user);

    /**
     * 查看近30天登陆
     * @return
     */
    int viewRecentLogin();
    /**
     * 查看历史登陆人数
     */
    int viewHistoryLogin();

    /**
     * 查看总注册人数
     * @return
     */
    int viewAllRegister();
}
