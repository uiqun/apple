package com.uiqun.service.impl;

import com.uiqun.dao.BomDao;
import com.uiqun.model.Bom;
import com.uiqun.service.BomService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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

    @Override
    public Workbook downloadbom(int bid,String bname) {
        List<Bom> boms = bomDao.queryBomByBid(bid);
        //2003版本的
        Workbook wk=new HSSFWorkbook();
        Sheet bomList = wk.createSheet("产品名称"+bname+"的Bom清单列表");
        bomList.setDefaultColumnWidth(20);
        for (int i = 0; i < boms.size()+1; i++) {
            Row row = bomList.createRow(i);
            if(i==0){
                row.createCell(0).setCellValue("型号");
                row.createCell(1).setCellValue("描述");
                row.createCell(2).setCellValue("品牌");
                row.createCell(3).setCellValue("单机用量");
                row.createCell(4).setCellValue("最小包装");
                row.createCell(5).setCellValue("单价");
                row.createCell(6).setCellValue("货期");
                continue;
            }
            row.createCell(0).setCellValue(boms.get(i-1).getPn());
            row.createCell(1).setCellValue(boms.get(i-1).getDes());
            row.createCell(2).setCellValue(boms.get(i-1).getMfg());
            row.createCell(3).setCellValue(boms.get(i-1).getUsage());
            row.createCell(4).setCellValue(boms.get(i-1).getMpq());
            row.createCell(5).setCellValue(boms.get(i-1).getPrice());
            row.createCell(6).setCellValue(boms.get(i-1).getDtime());
        }
        return  wk;
    }
}
