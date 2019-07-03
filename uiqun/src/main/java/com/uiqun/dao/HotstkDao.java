package com.uiqun.dao;

import com.uiqun.model.Hotstk;
import com.uiqun.utils.Pager;

import java.util.List;
import java.util.Map;

public interface HotstkDao {

    /**
     * excle模板插入型号
     * @return
     */
    int insertHotstks(List<List<Object>> Hotstks)throws Exception;

    int insertOneHotstk(Hotstk hotstk);

    int deleteHotstkByUid(int uid);
    /**
     * 查询全部
     * @param pager
     * @return
     * @throws Exception
     */
    List<Hotstk> queryHotstks(Pager<Hotstk> pager)throws Exception;

    /**
     * 前20个热卖
     * @return
     * @throws Exception
     */
    List<Hotstk> queryHotstksFromFindPrice(Map<String, Object> condition)throws Exception;

    /**
     * 查询行数
     * @param pager
     * @return
     * @throws Exception
     */
    int queryHotstkRow(Pager<Hotstk> pager)throws Exception;

    int deletXhotstk(int id);

}
