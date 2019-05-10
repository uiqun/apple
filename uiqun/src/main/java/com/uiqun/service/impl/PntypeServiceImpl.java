package com.uiqun.service.impl;

import com.uiqun.dao.PntypeDao;
import com.uiqun.model.Pntype;
import com.uiqun.service.PntypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class PntypeServiceImpl implements PntypeService {
    @Resource
    private PntypeDao pntypeDao;
    public List<Pntype> queryPntypes() {
        return pntypeDao.queryPntypes();
    }
}
