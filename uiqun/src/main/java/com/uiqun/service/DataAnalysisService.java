package com.uiqun.service;

import com.uiqun.model.DataAnalysis;
import com.uiqun.model.DataAnalysisbyVisitors;

import java.util.List;

public interface DataAnalysisService {
    /**
     * 获取近30天询价排名排行榜
     * @param ranking 前多少名
     * @param type 类型 1.询价 2.报价
     * @return
     */
    List<DataAnalysis> getRankingList(int ranking, int type);

    /**
     * 查询单个统计信息
     * @param dataAnalysis
     * @return
     */
    DataAnalysis queryOneData(DataAnalysis dataAnalysis);
    /**
     * 修改统计信息
     * @param dataAnalysis
     * @return
     */
    boolean updateStats(DataAnalysis dataAnalysis);

    /**
     * 插入新统计信息
     * @param dataAnalysis
     * @return
     */
    boolean insertDataAnalysis(DataAnalysis dataAnalysis);

    /**
     * 分页摸出统计信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<DataAnalysis> getDataList(int pageNum,int pageSize);

    /**
     * 插入新统计信息(访客数)
     * @param dataAnalysisbyVisitors
     * @return
     */
    int insertDataAnalysisbyVisitors(DataAnalysisbyVisitors dataAnalysisbyVisitors);
    /**
     * 修改统计信息(访客数)
     * @param dataAnalysisbyVisitors
     * @return
     */
    int updateStatsByDataAnalysisbyVisitors(DataAnalysisbyVisitors dataAnalysisbyVisitors);

    /**
     * 查询单个统计信息(访客数)
     * @return
     */
    DataAnalysisbyVisitors queryDataAnalysisbyVisitors();
}
