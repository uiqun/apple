package com.uiqun.service.impl;

import com.uiqun.dao.ServiceDao;
import com.uiqun.model.Service;
import com.uiqun.service.ServiceToService;
import com.uiqun.utils.Pager;

import javax.annotation.Resource;

@org.springframework.stereotype.Service
public class ServiceToServiceImpl implements ServiceToService {

    @Resource
    private ServiceDao serviceDao;

    @Override
    public boolean insertService(Service service) {
        try {
            return serviceDao.insertService(service)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Pager<Service> queryService(Pager<Service> pager) {
        try {
            pager.setTotalCount(serviceDao.queryServiceRows(pager));
            pager.setDatas(serviceDao.queryService(pager));
            return pager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteServiceById(int sid) {
        return serviceDao.deleteServiceById(sid)>0;
    }
}
