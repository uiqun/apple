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
        try {
            return pnDao.queryPns();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertPns(List<List<Object>> pns) {
        try {
            return pnDao.insertPns(pns)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkPn(Pn pn) {
        try {
            return pnDao.queryPn(pn)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addPn(Pn pn) {
        try {
            return pnDao.insertOnePn(pn)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Pn selectStkByMinPrice(Pn pn) {
        try {
            return pnDao.selectStkByMinPrice(pn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
