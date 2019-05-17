package com.uiqun.dao;

import com.uiqun.model.Hotstk;
import com.uiqun.utils.Pager;

import java.util.List;

public interface HotstkDao {

    /**
     * excle模板插入型号
     * @return
     */
    int insertHotstks(List<List<Object>> Hotstks)throws Exception;

    /**
     * 查询全部
     * @param pager
     * @return
     * @throws Exception
     */
    List<Hotstk> queryHotstks(Pager<Hotstk> pager)throws Exception;

    /**
     * 查询行数
     * @param pager
     * @return
     * @throws Exception
     */
    int queryHotstkRow(Pager<Hotstk> pager)throws Exception;

}
