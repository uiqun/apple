package com.uiqun.dao;

import com.uiqun.model.Mfg;
import com.uiqun.model.Pn;

import java.util.List;

public interface PnDao {
    /**
     * 查找全部
     * @return
     */
    List<Pn> queryPns();

    /**
     * excle模板插入型号
     * @return
     */
    int insertPns(List<List<Object>> pns);

    /**
     * 查找型号
     * @param pn
     * @return
     */
    int queryPn(Pn pn);

    /**
     * 插入单个型号
     * @param pn
     * @return
     */
    int insertOnePn(Pn pn);


}
