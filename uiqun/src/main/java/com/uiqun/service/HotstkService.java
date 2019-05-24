package com.uiqun.service;

import com.uiqun.model.Hotstk;
import com.uiqun.utils.Pager;

import java.util.List;
import java.util.Map;

public interface HotstkService {

    /**
     * excel模板插入型号
     * @return
     */
    boolean insertHotstks(List<List<Object>> hotstks)throws Exception;

    /**
     * 查找全部
     * @return
     */
    List<Hotstk> queryHotstks(Pager<Hotstk> pager)throws Exception;


    int queryRow(Pager<Hotstk> pager)throws Exception;

    /**
     * 前20个热卖
     * @return
     */
    List<Hotstk> queryHotstksFromFindPrice(Map<String,Object> condition);
}
