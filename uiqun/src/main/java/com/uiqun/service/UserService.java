package com.uiqun.service;

import com.uiqun.model.User;
import com.uiqun.model.Utype;

import java.util.List;

public interface UserService {
    User login(User user);

    List<User> queryVendor(Utype utype);
}
