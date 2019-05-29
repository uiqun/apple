package com.uiqun.dao;

import com.uiqun.model.Platinfo;

public interface PlatInfoDao {

    Platinfo queryPlatinfo();

    int updatePlatinfo(Platinfo platinfo);
}
