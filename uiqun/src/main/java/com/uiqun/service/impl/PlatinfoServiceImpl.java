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
    public Platinfo queryPlatinfo() {
        return platInfoDao.queryPlatinfo();
    }

    @Override
    public boolean updatePlatinfo(Platinfo platinfo) {
        return platInfoDao.updatePlatinfo(platinfo)>0;
    }
}
