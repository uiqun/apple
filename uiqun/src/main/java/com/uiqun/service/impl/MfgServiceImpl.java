package com.uiqun.service.impl;

import com.uiqun.dao.MfgDao;
import com.uiqun.model.Mfg;
import com.uiqun.model.Pn;
import com.uiqun.service.MfgService;
import com.uiqun.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MfgServiceImpl implements MfgService {
    @Resource
    private MfgDao mfgDao;

    public boolean addMfg(Mfg mfg) {
        try {
            return mfgDao.insertMfg(mfg)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkMfg(Mfg mfg) {
        try {
            return mfgDao.queryOneMfg(mfg)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Mfg> checkRfqPn(Pn pn) {
        try {
            return mfgDao.checkRfqPn(pn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Mfg getMfg(Mfg mfg) {
        try {
            return mfgDao.queryRfq(mfg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean modifyMfg(Mfg mfg) {
        try {
            return mfgDao.modifyMfg(mfg)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Workbook getMfgExcel() {
        List<Mfg> mfgs = mfgDao.queryAllMfg();
        //2003版本的
        Workbook wk=new HSSFWorkbook();
        Sheet mfgList = wk.createSheet("所有品牌列表");
        mfgList.setDefaultColumnWidth(20);
        for (int i = 0; i < mfgs.size()+1; i++) {
            Row row = mfgList.createRow(i);
            if(i==0){
                row.createCell(0).setCellValue("品牌编号");
                row.createCell(1).setCellValue("品牌名称");
                row.createCell(2).setCellValue("品牌商标");
                row.createCell(3).setCellValue("生产产品");
                row.createCell(4).setCellValue("公司简介");
                row.createCell(5).setCellValue("国籍");
                row.createCell(6).setCellValue("网址");
                continue;
            }
            row.createCell(0).setCellValue(mfgs.get(i-1).getMid());
            row.createCell(1).setCellValue(mfgs.get(i-1).getMfgName());
            row.createCell(2).setCellValue(mfgs.get(i-1).getMlogo());
            row.createCell(3).setCellValue(mfgs.get(i-1).getProduct());
            row.createCell(4).setCellValue(mfgs.get(i-1).getMprofile());
            row.createCell(5).setCellValue(mfgs.get(i-1).getCountry());
            row.createCell(6).setCellValue(mfgs.get(i-1).getWebsite());
        }
        return wk;
    }

    @Override
    public boolean uploadMfgList(MultipartFile smultipartfile) {
        try {
            List<List<Object>> uploadListByExcel = ExcelUtil.getUploadListByExcel(smultipartfile.getInputStream(),
                    smultipartfile.getOriginalFilename());
            mfgDao.truncateTable();
            mfgDao.resetTable();
            mfgDao.resetAllMfgInfo(uploadListByExcel);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
