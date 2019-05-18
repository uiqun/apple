package com.uiqun.dao;

import com.uiqun.model.Utype;

import java.util.List;

public interface UtypeDao {
    /**
     * 按照分类id查询子分类
     * @param utypeId
     * @return
     * @throws Exception
     */
    List<Utype> queryUtypes(int utypeId)throws Exception;

    /**
     * 根据子Id找出子信息和父信息
     * @param utypeId
     * @return
     * @throws Exception
     */
    Utype queryUtypeByIdFromChild(int utypeId)throws Exception;
}
