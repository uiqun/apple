package com.uiqun.dao;

import com.uiqun.model.Pn;

import java.util.List;

public interface PnDao {
    /**
     * 查找全部
     * @return
     */
    List<Pn> queryPns()throws Exception;

    /**
     * excle模板插入型号
     * @return
     */
    int insertPns(List<List<Object>> pns)throws Exception;

    /**
     * 查找型号
     * @param pn
     * @return
     */
    int queryPn(Pn pn)throws Exception;

    /**
     * 插入单个型号
     * @param pn
     * @return
     */
    int insertOnePn(Pn pn)throws Exception;


}
