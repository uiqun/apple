package com.uiqun.dao;

import com.uiqun.model.Rfq;
import com.uiqun.utils.Pager;

import java.util.List;
import java.util.Map;

public interface RfqDao {
    /**
     * 全部询价
     * @param pager
     * @return
     * @throws Exception
     */
    List<Rfq> queryRfqList(Pager<Rfq> pager)throws Exception;

    /**
     * 前20个询价
     * @param condition
     * @return
     * @throws Exception
     */
    List<Rfq> queryRfqListFromFindPrice(Map<String,Object> condition)throws Exception;

    /**
     * 查询总行数
     * @param pager
     * @return
     * @throws Exception
     */
    int queryRfqRows(Pager<Rfq> pager)throws Exception;

    /**
     * 添加询价
     * @param rfq
     * @return
     * @throws Exception
     */
    int insertRfq(Rfq rfq)throws Exception;

    /**
     * 删除询价
     * @param rfqno
     * @return
     * @throws Exception
     */
    int deleterfq(int rfqno)throws Exception;

    /**
     * 修改询价
     * @param rfqno
     * @return
     * @throws Exception
     */
    Rfq modifyrfq(int rfqno)throws Exception;

    /**
     * 按询价单号查询询价
     * @param rfqno
     * @return
     * @throws Exception
     */
    Rfq queryRfq(int rfqno)throws Exception;


    /**
     * 后台管理查询总行数
     * @param pager
     * @return
     */
    int queryRfqRowsByAdmin(Pager<Rfq> pager);

    /**
     * 后台管理分页查询询价
     * @param pager
     * @return
     */
    List<Rfq>  queryRfqByAdmin(Pager<Rfq> pager);

    /**
     * 删除询价
     * @param rfqid
     * @return
     */
    int deletXrfqByAdmin(int rfqid);
}
