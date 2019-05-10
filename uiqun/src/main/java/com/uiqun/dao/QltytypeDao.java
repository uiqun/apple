package com.uiqun.dao;

import com.uiqun.model.Qltytype;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface QltytypeDao {
    @Select("select * from qltytype")
    List<Qltytype> queryQltytype();
}
