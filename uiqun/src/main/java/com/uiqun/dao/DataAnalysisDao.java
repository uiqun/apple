package com.uiqun.dao;

import com.uiqun.model.DataAnalysis;
import com.uiqun.model.DataAnalysisbyVisitors;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataAnalysisDao {
    /**
     * 获取近30天询价排名排行榜
     * @param ranking 前多少名
     * @param type 类型 1.询价 2.报价
     * @return
     */
    List<DataAnalysis> getRankingList(@Param("ranking") int ranking, @Param("type") int type);

    /**
     * 分段摸出统计信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<DataAnalysis> getDataList(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);
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
    int updateStats(DataAnalysis dataAnalysis);

    /**
     * 插入新统计信息
     * @param dataAnalysis
     * @return
     */
    int insertDataAnalysis(DataAnalysis dataAnalysis);

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
