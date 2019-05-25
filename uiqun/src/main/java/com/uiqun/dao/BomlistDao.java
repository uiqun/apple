package com.uiqun.dao;

import com.uiqun.model.Bomlist;
import com.uiqun.utils.Pager;

import java.util.List;

public interface BomlistDao {
    /**
     * 添加bomlist记录
     * @param bomlist
     * @return
     * @throws Exception
     */
    int insertBomlist(Bomlist bomlist) throws Exception;

    /**
     * 分页查询bomlist
     * @param pager
     * @return
     * @throws Exception
     */
    List<Bomlist> queryBomlists(Pager<Bomlist> pager)throws Exception;

    /**
     * 按条件查询bomlist共有多少行
     * @param pager
     * @return
     * @throws Exception
     */
    int queryBomlistRow(Pager<Bomlist> pager)throws Exception;

    /**
     * 按照bid查询Bomlist
     * @param bomlist
     * @return
     * @throws Exception
     */
    Bomlist queryOneBomlist(Bomlist bomlist)throws Exception;

    /**
     * 更新bomlist
     * @param bomlist
     * @return
     */
    int updateBomList(Bomlist bomlist)throws Exception;

    int deleteXbomList(int bid);
}
