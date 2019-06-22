package com.uiqun.dao;

import com.uiqun.model.Service;
import com.uiqun.utils.Pager;

import java.util.List;

public interface ServiceDao {
    /**
     * 新增服务
     * @param service
     * @return
     * @throws Exception
     */

    int insertService(Service service)throws Exception;

    /**
     * 根据客户名称和服务名称查询用户服务信息
     * @return
     */
    List<Service> queryService(Pager<Service> pager);
    int queryServiceRows(Pager<Service> pager);

    int deleteServiceById(int id);
}
