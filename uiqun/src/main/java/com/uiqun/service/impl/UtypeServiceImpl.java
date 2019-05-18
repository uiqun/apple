package com.uiqun.service.impl;

import com.uiqun.dao.UtypeDao;
import com.uiqun.model.Utype;
import com.uiqun.service.UtypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UtypeServiceImpl implements UtypeService {
    @Resource
    private UtypeDao utypeDao;


    @Override
    public List<Utype> queryUtypes(int id) {
        try {
            return utypeDao.queryUtypes(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Utype queryUtypeByIdFromChild(int utypeId) {
        try {
            return utypeDao.queryUtypeByIdFromChild(utypeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
