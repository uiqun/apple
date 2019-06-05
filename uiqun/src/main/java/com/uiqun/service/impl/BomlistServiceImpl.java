package com.uiqun.service.impl;

import com.uiqun.dao.BomDao;
import com.uiqun.dao.BomlistDao;
import com.uiqun.model.Bomlist;
import com.uiqun.service.BomlistService;
import com.uiqun.service.PnService;
import com.uiqun.utils.ExcelUtil;
import com.uiqun.utils.Pager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

@Service
public class BomlistServiceImpl implements BomlistService {
    @Resource
    private BomlistDao bomlistDao;
    @Resource
    private BomDao bomDao;

    @Override
    public boolean insertBomlist(Bomlist bomlist){
        try {
            return bomlistDao.insertBomlist(bomlist)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Pager<Bomlist> queryBomlists(Pager<Bomlist> pager) {
        try {
            pager.setTotalCount(bomlistDao.queryBomlistRow(pager));
            pager.setDatas(bomlistDao.queryBomlists(pager));
            return pager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Bomlist getBomlist(Bomlist bomlist) {
        try {
            return bomlistDao.queryOneBomlist(bomlist);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean modifyBomList(Bomlist bomlist) {
        try {
            return bomlistDao.updateBomList(bomlist)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteXbom(Integer bomid) {
        try {
            bomlistDao.deleteXbomList(bomid);
            bomDao.deleteXbom(bomid);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean modifybom(MultipartFile multipartFile, Integer bomid, PnService pnService) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            List<List<Object>> boms = ExcelUtil.getBomListByExcel(inputStream,multipartFile.getOriginalFilename(),bomid,pnService);
            bomDao.deleteXbom(bomid);
            bomDao.insertBoms(boms);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
