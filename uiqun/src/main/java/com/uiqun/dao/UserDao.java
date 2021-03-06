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

    int updateSelfUser(User user)throws Exception;

    int saveUser(User user);

    User queryUserByPhone(@Param("mobile") String mobile);
    User querySelfUserByPhone(User user);

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

    /**
     * 根据区域查询用户信息
     * @param user
     * @return
     */
    List<User> queryUsersByAdmin(User user);

    /**
     * 后台插入用户信息
     * @param user
     * @return
     */
    int insertOneUserByAdmin(User user);

    /**
     * 后台修改用户信息
     * @param user
     * @return
     */
    int modifyUserByAdmin(User user);


}
