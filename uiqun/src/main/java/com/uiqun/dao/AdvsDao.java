package com.uiqun.dao;

import com.uiqun.model.Advs;

public interface AdvsDao {
    /**
     * 查询所有广告词
     * @return
     * @throws Exception
     */
    Advs queryAdvs()throws Exception;

    int updateAdvs(Advs advs)throws Exception;
}
