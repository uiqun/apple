package com.uiqun.service.impl;

import com.uiqun.dao.HotstkDao;
import com.uiqun.model.Hotstk;
import com.uiqun.model.User;
import com.uiqun.service.HotstkService;
import com.uiqun.utils.ExcelUtil;
import com.uiqun.utils.Pager;
import com.uiqun.utils.UpLoadUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    @Transactional(rollbackFor = Exception.class)
    public boolean uploadHotstkListByUid(User user, MultipartFile pmultipartfile) {
        try {
            //读取进来的列表
            List<List<Object>> uploadListByExcel = ExcelUtil.getUploadListByExcel(pmultipartfile.getInputStream(),
                    pmultipartfile.getOriginalFilename());
            if(uploadListByExcel.size()>500||uploadListByExcel.size()==0){
                return false;
            }
            List<Hotstk> hotstks = UpLoadUtil.tranceObject(uploadListByExcel, Hotstk.class);
            hotstkDao.deleteHotstkByUid(user.getUid());
            for (int i = 0; i < hotstks.size(); i++) {
                Hotstk hotstk = hotstks.get(i);
                hotstk.setUid(user.getUid());
                hotstk.setCompany(user.getCompany());
                hotstkDao.insertOneHotstk(hotstk);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
