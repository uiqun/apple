package com.uiqun.service.impl;

import com.uiqun.dao.PlatInfoDao;
import com.uiqun.model.Platinfo;
import com.uiqun.service.PlatinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PlatinfoServiceImpl implements PlatinfoService{
    @Resource
    private PlatInfoDao platInfoDao;

    @Override
    public Platinfo queryPlatinfoById(int id) {
        return platInfoDao.queryPlatinfoById(id);
    }

    @Override
    public boolean updatePlateinfos(Platinfo platinfo) {
        return platInfoDao.updatePlateinfos(platinfo)>0;
    }
}
