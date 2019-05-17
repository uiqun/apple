package com.uiqun.service;

import com.uiqun.model.Bomlist;
import com.uiqun.utils.Pager;

import java.util.List;

public interface BomlistService{
    boolean insertBomlist(Bomlist bomlist) throws Exception;
    List<Bomlist> queryBomlists(Pager<Bomlist> pager)throws Exception;
    boolean queryBomlistRow(Pager<Bomlist> pager)throws Exception;
}
