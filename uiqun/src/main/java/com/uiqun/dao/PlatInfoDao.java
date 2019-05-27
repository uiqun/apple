package com.uiqun.dao;

import com.uiqun.model.Platinfo;

public interface PlatInfoDao {

    Platinfo queryPlatinfoById(int id);

    int updatePlateinfos(Platinfo platinfo);
}
