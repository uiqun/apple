package com.uiqun.service.impl;

import com.uiqun.dao.HotstkDao;
import com.uiqun.model.Hotstk;
import com.uiqun.service.HotstkService;
import com.uiqun.utils.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class HotstkServiceImpl implements HotstkService {
    @Resource
    private HotstkDao hotstkDao;

    @Override
    public boolean insertHotstks(List<List<Object>> hotstks) throws Exception {
        return hotstkDao.insertHotstks(hotstks)>0;
    }

    @Override
    public Pager<Hotstk> queryHotstks(Pager<Hotstk> pager)  {
        try {
            pager.setTotalCount(hotstkDao.queryHotstkRow(pager));
            pager.setDatas(hotstkDao.queryHotstks(pager));
            return pager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public List<Hotstk> queryHotstksFromFindPrice(Map<String, Object> condition) {
        try {
            return hotstkDao.queryHotstksFromFindPrice(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deletXhotstk(int id) {
        try {
            return hotstkDao.deletXhotstk(id)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
