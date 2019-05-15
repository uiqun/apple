package com.uiqun.service;

import com.uiqun.model.Bom;

import java.util.List;

public interface BomService {

    boolean insertBoms(List<List<Object>> boms)throws Exception;

    Bom queryBomById(int id);
}
