package com.uiqun.dao;

import com.uiqun.model.Qltytype;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface QltytypeDao {
    /**
     * 查询所有质量标准
     * @return
     * @throws Exception
     */
    @Select("select * from qltytype")
    List<Qltytype> queryQltytype()throws Exception;
}
