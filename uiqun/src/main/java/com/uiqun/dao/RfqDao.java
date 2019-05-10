package com.uiqun.dao;

import com.uiqun.model.Rfq;
import com.uiqun.utils.Pager;

import java.util.List;

public interface RfqDao {
    List<Rfq> queryRfqList(Pager<Rfq> pager);
    int queryRfqRows(Pager<Rfq> pager);
}
