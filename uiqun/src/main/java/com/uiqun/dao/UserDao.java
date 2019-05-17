package com.uiqun.dao;

import com.uiqun.model.User;
import com.uiqun.model.Utype;

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
     * 查询所有供应商
     * @param utype
     * @return
     */
    List<User> queryVendor(Utype utype);
}
