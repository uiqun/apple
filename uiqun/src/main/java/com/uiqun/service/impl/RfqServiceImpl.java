package com.uiqun.service.impl;

import com.uiqun.dao.RfqDao;
import com.uiqun.model.Rfq;
import com.uiqun.service.RfqService;
import com.uiqun.utils.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RfqServiceImpl implements RfqService {

    @Resource
    private RfqDao rfqDao;

    public Pager<Rfq> queryRfqList(Pager<Rfq> pager) {
        pager.setTotalCount(rfqDao.queryRfqRows(pager));
        pager.setDatas(rfqDao.queryRfqList(pager));
        return pager;
    }
}
