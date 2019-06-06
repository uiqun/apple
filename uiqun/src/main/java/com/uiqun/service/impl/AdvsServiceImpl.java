package com.uiqun.service.impl;

import com.uiqun.dao.AdvsDao;
import com.uiqun.model.Advs;
import com.uiqun.service.AdvsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdvsServiceImpl implements AdvsService {
    @Resource
    private AdvsDao advsDao;

    @Override
    public Advs queryAdvs() {
        try {
            return advsDao.queryAdvs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateAdvs(Advs advs){
        try {
            return advsDao.updateAdvs(advs)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
