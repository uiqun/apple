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
        return utypeDao.queryUtypes(id);
    }
}
