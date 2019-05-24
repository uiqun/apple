package com.uiqun.dao;

import com.uiqun.model.Btype;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BtypeDao {

    /**
     * 按照分类id查询子分类
     * @return
     * @throws Exception
     */
    @Select("select id btypeId,name btypeName from bomtype")
    List<Btype> queryBtypes()throws Exception;
}
