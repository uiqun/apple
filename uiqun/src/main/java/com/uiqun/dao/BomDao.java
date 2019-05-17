package com.uiqun.dao;

import com.uiqun.model.Bom;

import java.util.List;

public interface BomDao {

    int insertBoms(List<List<Object>> boms)throws Exception;

    Bom queryBomById(int id);
}
