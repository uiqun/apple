package com.uiqun.service;

import com.uiqun.model.Mfg;
import com.uiqun.model.Pn;

import java.util.List;

public interface MfgService {
    /**
     * 添加品牌
     * @param mfg
     * @return
     */
    boolean addMfg(Mfg mfg);

    /**
     * 检查该品牌是否存在
     * @param mfg
     * @return
     */
    boolean checkMfg(Mfg mfg);


    /**
     * 按型号查询供应商信息
     * @param pn
     * @return
     */
    List<Mfg> checkRfqPn(Pn pn);
}
