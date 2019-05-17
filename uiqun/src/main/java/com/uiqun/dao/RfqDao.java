package com.uiqun.dao;

import com.uiqun.model.Rfq;
import com.uiqun.utils.Pager;

import java.util.List;

public interface RfqDao {
    List<Rfq> queryRfqList(Pager<Rfq> pager)throws Exception;
    int queryRfqRows(Pager<Rfq> pager)throws Exception;
    int insertRfq(Rfq rfq)throws Exception;
    int deleterfq(int rfqno)throws Exception;
    Rfq modifyrfq(int rfqno);
    Rfq queryRfq(int rfqno);
}
