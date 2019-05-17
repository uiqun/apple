package com.uiqun.service.impl;

import com.uiqun.dao.BomlistDao;
import com.uiqun.model.Bomlist;
import com.uiqun.service.BomlistService;
import com.uiqun.utils.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BomlistServiceImpl implements BomlistService {
    @Resource
    private BomlistDao bomlistDao;

    @Override
    public boolean insertBomlist(Bomlist bomlist) throws Exception {
        return bomlistDao.insertBomlist(bomlist)>0;
    }

    @Override
    public List<Bomlist> queryBomlists(Pager<Bomlist> pager) throws Exception {
        return bomlistDao.queryBomlists(pager);
    }

    @Override
    public boolean queryBomlistRow(Pager<Bomlist> pager) throws Exception {
        return bomlistDao.queryBomlistRow(pager)>0;
    }
}
