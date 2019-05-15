package com.uiqun.service.impl;

import com.uiqun.dao.BomDao;
import com.uiqun.model.Bom;
import com.uiqun.service.BomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BomServiceImpl implements BomService {
    @Resource
    private BomDao bomDao;

    @Override
    public boolean insertBoms(List<List<Object>> boms) throws Exception {
        return bomDao.insertBoms(boms)>0;
    }

    @Override
    public Bom queryBomById(int id) {
        return bomDao.queryBomById(id);
    }
}
