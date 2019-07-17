package com.uiqun.utils;

import com.alibaba.fastjson.JSON;
import com.uiqun.dao.DataAnalysisDao;
import com.uiqun.model.DataAnalysis;
import com.uiqun.model.DataAnalysisbyVisitors;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DataAnalysisUtil {
    @Resource
    private  DataAnalysisDao dataAnalysisDao;
    /**
     * 在线人数
     * @return
     */
    public static long getOnlineNum(){
        return  SessionCounter.getActiveSessions();
    }


    public  void modifyOrInsertDataAnalysis(int type,String pn) {
        //查询是否存在该型号的信息
        DataAnalysis dataAnalysis = dataAnalysisDao.queryOneData(new DataAnalysis(type, pn));
        if (dataAnalysis != null) {
            //历史查询加1
            dataAnalysis.setCountByHistory(dataAnalysis.getCountByHistory() + 1);
            ArrayList<Integer> arrayList = JSON.parseObject(dataAnalysis.getCountByMonthOfJson(), ArrayList.class);
            arrayList.set(dataAnalysis.getDayCount(), arrayList.get(dataAnalysis.getDayCount()) + 1);
            int x = 0;
            for (int i = 0; i < arrayList.size(); i++) {
                x += arrayList.get(i);
            }
            //修改近30天的询价总数(JSON)
            dataAnalysis.setCountByMonthOfJson(JSON.toJSONString(arrayList));
            //修改近30天的询价总数
            dataAnalysis.setCountByMonth(x);
            //更新统计信息
            dataAnalysisDao.updateStats(dataAnalysis);
        } else {
            dataAnalysis = new DataAnalysis(type, pn);
            //创建30天记录器
            List<Integer> Json = new ArrayList<>();
            Json.add(1);
            //记录已经录多少天
            dataAnalysis.setDayCount(0);
            //记录历史查询数
            dataAnalysis.setCountByHistory(1);
            //记录当前近30天询价数
            dataAnalysis.setCountByMonth(1);
            //记录当前近30天询价数(JSON格式)
            dataAnalysis.setCountByMonthOfJson(JSON.toJSONString(Json));
            //最后一次询价时间
            dataAnalysis.setSelLastDate(new Date());
            //新建统计信息
            dataAnalysisDao.insertDataAnalysis(dataAnalysis);
        }

    }


    /**
     * 偏移统计信息指针，修改统计信息
     */
    @Scheduled(cron = "0 0 0 * * *")
    private void skewingPointerByData(){
        int pageNum =1;
        int pageSize=20;
        List<DataAnalysis> dataList = dataAnalysisDao.getDataList((pageNum-1)*pageSize, pageSize);
        while(dataList!=null&&dataList.size()>0){
            for (int i = 0; i < dataList.size(); i++) {
                DataAnalysis dataAnalysis = dataList.get(i);
                //修改天数计数器
                Integer dayCount = dataAnalysis.getDayCount();
                //修改近30天数据(JSON类型)
                ArrayList<Integer> arrayList = JSON.parseObject(dataAnalysis.getCountByMonthOfJson(), ArrayList.class);
                if(arrayList.size()<30) {
                    arrayList.add(0);
                }
                if(dayCount+1<30&&dayCount+1<arrayList.size()) {
                    arrayList.set(dayCount+1, 0);
                }else if(dayCount+1==30){
                    arrayList.set(0, 0);
                }
                dataAnalysis.setCountByMonthOfJson(JSON.toJSONString(arrayList));
                dayCount++;
                if(dayCount<30){
                    dataAnalysis.setDayCount(dayCount);
                }else{
                    dataAnalysis.setDayCount(0);
                }
                //修改近30天数据(Integer类型)
                int count = 0;
                for (int j = 0; j < arrayList.size(); j++) {
                    count+=arrayList.get(j);
                }
                dataAnalysis.setCountByMonth(count);
            }
            for (DataAnalysis dataAnalysis : dataList) {
                dataAnalysisDao.updateStats(dataAnalysis);
            }
            pageNum++;
            dataList = dataAnalysisDao.getDataList((pageNum-1)*pageSize, pageSize);
        }
    }

    //偏移统计信息指针，修改统计信息(访客数)
    @Scheduled(cron = "0 0 0 * * *")
    private void skewingPointerByVisitors(){
        DataAnalysisbyVisitors dataAnalysisbyVisitors = dataAnalysisDao.queryDataAnalysisbyVisitors();
        if(dataAnalysisbyVisitors!=null){
            //修改天数计数器
            Integer dayCount = dataAnalysisbyVisitors.getDayCount();
            //修改近30天数据(JSON类型)
            ArrayList<Integer> arrayList = JSON.parseObject(dataAnalysisbyVisitors.getVisitorsByMonthOfJson(), ArrayList.class);
            if(arrayList.size()<30) {
                arrayList.add(0);
            }
            if(dayCount+1<30&&dayCount+1<arrayList.size()) {
                arrayList.set(dayCount+1, 0);
            }else if(dayCount+1==30){
                arrayList.set(0, 0);
            }
            dataAnalysisbyVisitors.setVisitorsByMonthOfJson(JSON.toJSONString(arrayList));
            dayCount++;
            if(dayCount<30){
                dataAnalysisbyVisitors.setDayCount(dayCount);
            }else{
                dataAnalysisbyVisitors.setDayCount(0);
            }
            //修改近30天数据(Integer类型)
            int count = 0;
            for (int j = 0; j < arrayList.size(); j++) {
                count+=arrayList.get(j);
            }
            dataAnalysisbyVisitors.setVisitorsByMonth(count);
            dataAnalysisDao.updateStatsByDataAnalysisbyVisitors(dataAnalysisbyVisitors);
        }
    }
}
