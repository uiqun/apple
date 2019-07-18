package com.uiqun.service.impl;

import com.uiqun.dao.PnDao;
import com.uiqun.model.Pn;
import com.uiqun.service.PnService;
import com.uiqun.utils.ExcelUtil;
import com.uiqun.utils.UpLoadUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
@Service
public class PnServiceImpl implements PnService {
    @Resource
    private PnDao pnDao;

    public List<Pn> queryPns() {
        try {
            return pnDao.queryPns();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertPns(List<List<Object>> pns) {
        try {
            return pnDao.insertPns(pns)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkPn(Pn pn) {
        try {
            return pnDao.queryPn(pn)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addPn(Pn pn) {
        try {
            return pnDao.insertOnePn(pn)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Pn selectStkByMinPrice(Pn pn) {
        try {
            return pnDao.selectStkByMinPrice(pn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Pn getPnByAdmin(Pn pn) {
        try {
            return pnDao.getPnByAdmin(pn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean modifyPnByAdmin(Pn pn) {
        try {
            return pnDao.modifyPnByAdmin(pn)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean modifyPnByAdminByOne(Pn pn) {
        try {
            return pnDao.modifyPnByAdminByOne(pn)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Workbook downExcelByPn(Pn pn) {
        List<Pn> pns = pnDao.queryPnsByAdmin(pn);
        //2003版本的
        Workbook wk=new HSSFWorkbook();
        Sheet bomList = wk.createSheet("型号列表");
        bomList.setDefaultColumnWidth(20);
        for (int i = 0; i < pns.size()+1; i++) {
            Row row = bomList.createRow(i);
            if(i==0){
                row.createCell(0).setCellValue("型号编码");
                row.createCell(1).setCellValue("型号分类");
                row.createCell(2).setCellValue("型号");
                row.createCell(3).setCellValue("描述");
                row.createCell(4).setCellValue("厂牌");
                row.createCell(5).setCellValue("封装");
                row.createCell(6).setCellValue("包装方式");
                row.createCell(7).setCellValue("最小包装");
                row.createCell(8).setCellValue("单价");
                row.createCell(9).setCellValue("规格");
                row.createCell(10).setCellValue("规格书链接");
                continue;
            }
            row.createCell(0).setCellValue(ExcelUtil.setResponseValue(pns.get(i-1).getPid()));
            row.createCell(1).setCellValue(ExcelUtil.setResponseValue(pns.get(i-1).getPtype()));
            row.createCell(2).setCellValue(ExcelUtil.setResponseValue(pns.get(i-1).getPn()));
            row.createCell(3).setCellValue(ExcelUtil.setResponseValue(pns.get(i-1).getDes()));
            row.createCell(4).setCellValue(ExcelUtil.setResponseValue(pns.get(i-1).getMfg()));
            row.createCell(5).setCellValue(ExcelUtil.setResponseValue(pns.get(i-1).getPkge()));
            row.createCell(6).setCellValue(ExcelUtil.setResponseValue(pns.get(i-1).getPack()));
            row.createCell(7).setCellValue(ExcelUtil.setResponseValue(pns.get(i-1).getMpq()));
            row.createCell(8).setCellValue(ExcelUtil.setResponseValue(pns.get(i-1).getPrice()));
            row.createCell(9).setCellValue(ExcelUtil.setResponseValue(pns.get(i-1).getSpeck()));
            row.createCell(10).setCellValue(ExcelUtil.setResponseValue(pns.get(i-1).getDatalink()));
        }
        return  wk;
    }

    @Override
    public boolean uploadPnList(MultipartFile pmultipartfile) {
        try {
            //读取进来的列表
            List<List<Object>> uploadListByExcel = ExcelUtil.getUploadListByExcel(pmultipartfile.getInputStream(),
                    pmultipartfile.getOriginalFilename());
            List<Pn> pns = UpLoadUtil.tranceObject(uploadListByExcel, Pn.class);
            for (int i = 0; i < pns.size(); i++) {
                Pn pn = pns.get(i);
                pn.setPtype(pn.getPtype().split(" ")[0]);
                if(pn.getPid()==null||pn.getPid()==0){
                    pnDao.insertOnePnByAdmin(pn);
                }else {
                    pnDao.modifyPnByAdmin(pn);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
