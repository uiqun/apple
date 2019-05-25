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
    int insertMfg(Mfg mfg)throws Exception;

    /**
     * 查询有无供应商
     * @param mfg
     * @return
     */
    @Select("select count(mfgName) from mfglist where mfgName = #{mfgName}")
    int queryOneMfg(Mfg mfg)throws Exception;

    /**
     * 按型号查询品牌信息
     * @param pn
     * @return
     */
    @Select("select m.* from mfglist m  left join pnlist p on m.mfgName=p.mfg WHERE p.pn=#{pn}")
    List<Mfg> checkRfqPn(Pn pn)throws Exception;

    /**
     * 按id查询品牌信息
     * @param mfg
     * @return
     */
    @Select("select * from mfglist  WHERE mid=#{mid}")
    Mfg queryRfq(Mfg mfg);
}
