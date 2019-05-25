package com.uiqun.service;

import com.uiqun.model.Bomlist;
import com.uiqun.utils.Pager;

public interface BomlistService{
    /**
     * 获取是否成功添加bomlist的结果
     * @param bomlist
     * @return
     */
    boolean insertBomlist(Bomlist bomlist);

    /**
     * 获取分页后的bom数据
     * @param pager
     * @return
     */
    Pager<Bomlist> queryBomlists(Pager<Bomlist> pager);
    /**
     * 按照bid查询Bomlist
     * @param bomlist
     * @return
     * @throws Exception
     */
    Bomlist getBomlist(Bomlist bomlist);

    boolean modifyBomList(Bomlist bomlist);
}
