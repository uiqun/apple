package com.uiqun.dao;

import com.uiqun.model.Utype;

import java.util.List;

public interface UtypeDao {

    List<Utype> queryUtypes(int id)throws Exception;

}
