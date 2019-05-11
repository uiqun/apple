package com.uiqun.service.impl;

import com.uiqun.dao.UserDao;
import com.uiqun.model.User;
import com.uiqun.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userdao;


    public User login(User user) {
        try {
            return userdao.queryUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
