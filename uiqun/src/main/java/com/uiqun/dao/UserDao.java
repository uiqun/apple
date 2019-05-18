package com.uiqun.dao;

import com.uiqun.model.User;
import com.uiqun.model.Vendors;

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
}
