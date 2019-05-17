package com.uiqun.dao;

import com.uiqun.model.Pntype;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PntypeDao {
    /**
     * 查询所有型号类型
     * @return
     * @throws Exception
     */
    @Select("select * from pntype")
    List<Pntype> queryPntypes()throws Exception;
}
