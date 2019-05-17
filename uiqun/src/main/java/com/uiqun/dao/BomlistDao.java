package com.uiqun.dao;

import com.uiqun.model.Bomlist;
import com.uiqun.utils.Pager;

import java.util.List;

public interface BomlistDao {
    int insertBomlist(Bomlist bomlist) throws Exception;
    List<Bomlist> queryBomlists(Pager<Bomlist> pager)throws Exception;
    int queryBomlistRow(Pager<Bomlist> pager)throws Exception;
}
