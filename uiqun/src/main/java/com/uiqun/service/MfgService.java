package com.uiqun.service;

import com.uiqun.model.Mfg;

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
}
