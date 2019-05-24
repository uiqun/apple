package com.uiqun.service.impl;

import com.uiqun.dao.UserDao;
import com.uiqun.model.User;
import com.uiqun.model.Vendors;
import com.uiqun.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}
