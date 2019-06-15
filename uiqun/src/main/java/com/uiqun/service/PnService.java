package com.uiqun.service;

import com.uiqun.model.Pn;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PnService {
    /**
     * 查找全部
     * @return
     */
    List<Pn> queryPns();

    /**
     * excle模板插入型号
     * @return
     */
    boolean insertPns(List<List<Object>> pns);

    /**
     * 查看型号是否存在
     * @param pn
     * @return
     */
    boolean checkPn(Pn pn);

    /**
     * 添加型号
     * @param pn
     * @return
     */
    boolean addPn(Pn pn);

    /**
     * 按型号匹配最优
     * @param pn
     * @return
     */
    Pn selectStkByMinPrice(Pn pn);

    /**
     * 后台型号获取
     * @param pn
     * @return
     */
    Pn getPnByAdmin(Pn pn);

    /**
     * 修改型号信息
     * @param pn
     * @return
     */
    boolean modifyPnByAdmin(Pn pn);

    /**
     * 获取下载的
     * @param pn
     * @return
     */
    Workbook downExcelByPn(Pn pn);

    boolean uploadPnList(MultipartFile pmultipartfile);
}
