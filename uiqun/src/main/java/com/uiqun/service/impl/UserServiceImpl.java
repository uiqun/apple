package com.uiqun.service.impl;

import com.uiqun.dao.AreaDao;
import com.uiqun.dao.UserDao;
import com.uiqun.model.Area;
import com.uiqun.model.User;
import com.uiqun.model.Vendors;
import com.uiqun.service.UserService;
import com.uiqun.utils.DateUtil;
import org.springframework.stereotype.Service;

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
    public boolean saveUser(User user) {
        return userdao.updateUser(user)>0;
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
    public List<Area> queryAreas() {
        try {
            return areaDao.queryAreas();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
