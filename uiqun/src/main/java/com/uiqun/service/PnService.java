package com.uiqun.service;

import com.uiqun.model.Pn;

import java.util.List;

public interface PnService {
    /**
     * 查找全部
     * @return
     */
    List<Pn> queryPns();

    /**
     * excle模板插入型号
     * @return
     */
    boolean insertPns(List<List<Object>> pns);

    /**
     * 查看型号是否存在
     * @param pn
     * @return
     */
    boolean checkPn(Pn pn);

    /**
     * 添加型号
     * @param pn
     * @return
     */
    boolean addPn(Pn pn);
}
