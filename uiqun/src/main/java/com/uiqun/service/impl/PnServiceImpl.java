package com.uiqun.service.impl;

import com.uiqun.dao.PnDao;
import com.uiqun.model.Pn;
import com.uiqun.service.PnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class PnServiceImpl implements PnService {
    @Resource
    private PnDao pnDao;

    public List<Pn> queryPns() {
        return pnDao.queryPns();
    }

    public boolean insertPns(List<List<Object>> pns) {
        return pnDao.insertPns(pns)>0;
    }

    @Override
    public boolean checkPn(Pn pn) {
        return pnDao.queryPn(pn)>0;
    }

    @Override
    public boolean addPn(Pn pn) {
        return pnDao.insertOnePn(pn)>0;
    }
}
