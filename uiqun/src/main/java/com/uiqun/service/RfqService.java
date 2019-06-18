package com.uiqun.service;

import com.uiqun.model.Rfq;
import com.uiqun.utils.Pager;

import java.util.List;
import java.util.Map;

public interface RfqService {
    Pager<Rfq> queryRfqList(Pager<Rfq> pager);
    boolean addRfq(Rfq rfq);
    boolean deleterfq(int rfqno);
    Rfq modifyrfq(int rfqno);
    Rfq queryRfq(int rfqno);
    /**
     * 前20个询价
     * @param condition
     * @return
     * @throws Exception
     */
    List<Rfq> queryRfqListFromFindPrice(Map<String, Object> condition);


     boolean deletXrfqByAdmin(int rfqid);

    Pager<Rfq> queryRfqByAdmin(Pager<Rfq> pager);
}
