package com.uiqun.dao;

import com.uiqun.model.User;
import com.uiqun.model.Utype;

import java.util.List;

public interface UserDao {
    User queryUser(User user)throws Exception;

    List<User> queryVendor(Utype utype);
}
