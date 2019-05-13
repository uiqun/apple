package com.uiqun.service;

import com.uiqun.model.Rfq;
import com.uiqun.utils.Pager;

public interface RfqService {
    Pager<Rfq> queryRfqList(Pager<Rfq> pager);
    boolean addRfq(Rfq rfq);
    boolean deleterfq(int rfqno);
    Rfq modifyrfq(int rfqno);
}
