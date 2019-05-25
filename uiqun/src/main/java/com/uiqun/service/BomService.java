package com.uiqun.service;

import com.uiqun.model.Bom;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface BomService {
    /**
     * 导入excel表内的bom信息
     * @param boms
     * @return
     * @throws Exception
     */
    boolean insertBoms(List<List<Object>> boms)throws Exception;

    /**
     * 根据id获取bom
     * @param id
     * @return
     */
    Bom queryBomById(int id);

    /**
     * 根据bomList表的bid生成2003版本的excel文件
     * @param bid
     * @return
     */
    Workbook downloadbom(int bid,String bname);
}
