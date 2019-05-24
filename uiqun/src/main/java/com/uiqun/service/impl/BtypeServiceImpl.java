package com.uiqun.service.impl;

import com.uiqun.dao.BtypeDao;
import com.uiqun.model.Btype;
import com.uiqun.service.BtypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BtypeServiceImpl implements BtypeService {
    @Resource
    private BtypeDao btypeDao;
    public List<Btype> queryBtypes() {
        try {
            return btypeDao.queryBtypes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
