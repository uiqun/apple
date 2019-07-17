package com.uiqun.service.impl;

import com.alibaba.fastjson.JSON;
import com.uiqun.dao.DataAnalysisDao;
import com.uiqun.model.DataAnalysis;
import com.uiqun.model.DataAnalysisbyVisitors;
import com.uiqun.service.DataAnalysisService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class DataAnalysisServiceImpl implements DataAnalysisService {
    @Resource
    private DataAnalysisDao dataAnalysisDao;
    @Override
    public List<DataAnalysis> getRankingList(int ranking, int type) {
        return dataAnalysisDao.getRankingList(ranking,type);
    }



    /**
     * 偏移统计信息指针，修改统计信息
     */
    @Scheduled(cron = "0 52 0 * * ?")
    private void skewingPointerByData(){
        System.out.println(11112);

    }

    @Override
    public DataAnalysis queryOneData(DataAnalysis dataAnalysis) {
        return dataAnalysisDao.queryOneData(dataAnalysis);
    }

    @Override
    public boolean updateStats(DataAnalysis dataAnalysis) {
        return dataAnalysisDao.updateStats(dataAnalysis)>0;
    }

    @Override
    public boolean insertDataAnalysis(DataAnalysis dataAnalysis) {
        return dataAnalysisDao.insertDataAnalysis(dataAnalysis)>0;
    }

    @Override
    public List<DataAnalysis> getDataList(int pageNum, int pageSize) {
        return dataAnalysisDao.getDataList(pageNum,pageSize);
    }

    @Override
    public int insertDataAnalysisbyVisitors(DataAnalysisbyVisitors dataAnalysisbyVisitors) {
        return dataAnalysisDao.insertDataAnalysisbyVisitors(dataAnalysisbyVisitors);
    }

    @Override
    public int updateStatsByDataAnalysisbyVisitors(DataAnalysisbyVisitors dataAnalysisbyVisitors) {
        return dataAnalysisDao.updateStatsByDataAnalysisbyVisitors(dataAnalysisbyVisitors);
    }

    @Override
    public DataAnalysisbyVisitors queryDataAnalysisbyVisitors() {
        return dataAnalysisDao.queryDataAnalysisbyVisitors();
    }
}
