package com.uiqun.dao;

import com.uiqun.model.Mfg;
import com.uiqun.model.Pn;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MfgDao {
    /**
     * 新增供应商信息
     * @param mfg
     * @return
     */
    @Insert("insert into mfglist(MID ,mfgName ,mlogo ,product ,mprofile ,country ,website) " +
            "values(#{mid}  ,#{mfgName} ,#{mlogo} ,#{product} ,#{mprofile} ,#{country} ,#{website})")
    int insertMfg(Mfg mfg);

    /**
     * 查询有无供应商
     * @param mfg
     * @return
     */
    @Select("select count(mfgName) from mfglist where mfgName = #{mfgName}")
    int queryOneMfg(Mfg mfg);

    /**
     * 按型号查询品牌信息
     * @param pn
     * @return
     */
    List<Mfg> checkRfqPn(Pn pn);
}
