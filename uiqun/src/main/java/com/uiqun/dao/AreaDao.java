package com.uiqun.dao;

import com.uiqun.model.Area;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AreaDao {

    /**
     * 查询所有区域名
     * @return
     * @throws Exception
     */
    @Select("select areaId, areaAbbr,areaName from area")
    List<Area> queryAreas()throws Exception;
}
