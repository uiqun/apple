package com.uiqun.dao;

import com.uiqun.model.Mfg;
import com.uiqun.model.Pn;

import java.util.List;

public interface MfgDao {
    /**
     * 新增供应商信息
     * @param mfg
     * @return
     */
    int insertMfg(Mfg mfg)throws Exception;

    /**
     * 查询有无供应商
     * @param mfg
     * @return
     */
    int queryOneMfg(Mfg mfg)throws Exception;

    /**
     * 按型号查询品牌信息
     * @param pn
     * @return
     */
    List<Mfg> checkRfqPn(Pn pn)throws Exception;

    /**
     * 按id查询品牌信息
     * @param mfg
     * @return
     */
    Mfg queryRfq(Mfg mfg);

    int modifyMfg(Mfg mfg);

    List<Mfg> queryAllMfg();

    void resetAllMfgInfo(List<List<Object>> uploadListByExcel);

    void truncateTable();
}
