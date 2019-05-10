package com.uiqun.service.impl;

import com.uiqun.dao.MfgDao;
import com.uiqun.model.Mfg;
import com.uiqun.model.Pn;
import com.uiqun.service.MfgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MfgServiceImpl implements MfgService {
    @Resource
    private MfgDao mfgDao;

    public boolean addMfg(Mfg mfg) {
        return mfgDao.insertMfg(mfg)>0;
    }

    @Override
    public boolean checkMfg(Mfg mfg) {
        return mfgDao.queryOneMfg(mfg)>0;
    }

    @Override
    public List<Mfg> checkRfqPn(Pn pn) {
        return mfgDao.checkRfqPn(pn);
    }
}
