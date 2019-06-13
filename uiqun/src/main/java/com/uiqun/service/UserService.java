package com.uiqun.service;

import com.uiqun.model.Area;
import com.uiqun.model.User;
import com.uiqun.model.Vendors;

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

    User queryUserDetail(User user);

    boolean updateUser(User user);

    boolean queryUserByPhone(String moblie);

    List<Area> queryAreas();
}
