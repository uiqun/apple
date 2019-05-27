package com.uiqun.service;

import com.uiqun.model.Platinfo;

public interface PlatinfoService {

    Platinfo queryPlatinfoById(int id);

    boolean updatePlateinfos(Platinfo platinfo);
}
