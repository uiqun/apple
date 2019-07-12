package com.uiqun.utils;

import com.alibaba.fastjson.JSON;
import com.uiqun.dao.DataAnalysisDao;
import com.uiqun.model.DataAnalysis;
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
}
