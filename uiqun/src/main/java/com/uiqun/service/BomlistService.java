package com.uiqun.service;

import com.uiqun.model.Bomlist;
import com.uiqun.utils.Pager;

public interface BomlistService{
    boolean insertBomlist(Bomlist bomlist);
    Pager<Bomlist> queryBomlists(Pager<Bomlist> pager);
}
