package com.uiqun.dao;

import com.uiqun.model.Pntype;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PntypeDao {
    @Select("select * from pntype")
    List<Pntype> queryPntypes();
}
