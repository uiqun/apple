package com.uiqun.service;

import com.uiqun.model.Utype;

import java.util.List;

public interface UtypeService {
    List<Utype> queryUtypes(int id);
    /**
     * 根据子Id找出子信息和父信息
     * @param utypeId
     * @return
     * @throws Exception
     */
    Utype queryUtypeByIdFromChild(int utypeId);
}
