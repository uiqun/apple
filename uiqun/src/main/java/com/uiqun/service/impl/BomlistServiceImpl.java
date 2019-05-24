package com.uiqun.service.impl;

import com.uiqun.dao.BomlistDao;
import com.uiqun.model.Bomlist;
import com.uiqun.service.BomlistService;
import com.uiqun.utils.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BomlistServiceImpl implements BomlistService {
    @Resource
    private BomlistDao bomlistDao;

    @Override
    public boolean insertBomlist(Bomlist bomlist){
        try {
            return bomlistDao.insertBomlist(bomlist)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Pager<Bomlist> queryBomlists(Pager<Bomlist> pager) {
        try {
            pager.setTotalCount(bomlistDao.queryBomlistRow(pager));
            pager.setDatas(bomlistDao.queryBomlists(pager));
            return pager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
