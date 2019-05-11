package com.uiqun.service.impl;

import com.uiqun.dao.QltytypeDao;
import com.uiqun.model.Qltytype;
import com.uiqun.service.QltytypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class QltytypeServiceImpl implements QltytypeService {
    @Resource
    private QltytypeDao qltytypeDao;
    public List<Qltytype> queryQltytype() {
        try {
            return qltytypeDao.queryQltytype();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
