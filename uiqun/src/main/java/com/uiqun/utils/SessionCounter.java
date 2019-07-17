package com.uiqun.utils;

import com.alibaba.fastjson.JSON;
import com.uiqun.dao.DataAnalysisDao;
import com.uiqun.model.DataAnalysis;
import com.uiqun.model.DataAnalysisbyVisitors;

import javax.annotation.Resource;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebListener
public class SessionCounter implements HttpSessionListener {
    private static int activeSessions = 0;
    @Resource
    private DataAnalysisDao dataAnalysisDao;

    //session创建时执行
    public void sessionCreated(HttpSessionEvent se) {
        DataAnalysisbyVisitors dataAnalysis = dataAnalysisDao.queryDataAnalysisbyVisitors();
        if (dataAnalysis != null) {
            //历史查询加1
            dataAnalysis.setHistoricalVisitors(dataAnalysis.getHistoricalVisitors() + 1);
            ArrayList<Integer> arrayList = JSON.parseObject(dataAnalysis.getVisitorsByMonthOfJson(), ArrayList.class);
            arrayList.set(dataAnalysis.getDayCount(), arrayList.get(dataAnalysis.getDayCount())+1);
            int x = 0;
            for (int i = 0; i < arrayList.size(); i++) {
                x += arrayList.get(i);
            }
            //修改近30天的询价总数(JSON)
            dataAnalysis.setVisitorsByMonthOfJson(JSON.toJSONString(arrayList));
            //修改近30天的询价总数
            dataAnalysis.setVisitorsByMonth(x);
            //更新统计信息
            dataAnalysisDao.updateStatsByDataAnalysisbyVisitors(dataAnalysis);
        } else {
            dataAnalysis = new DataAnalysisbyVisitors();
            //创建30天记录器
            List<Integer> Json = new ArrayList<>();
            Json.add(1);
            //记录已经录多少天
            dataAnalysis.setDayCount(0);
            //记录历史查询数
            dataAnalysis.setHistoricalVisitors(1);
            //记录当前近30天询价数
            dataAnalysis.setVisitorsByMonth(1);
            //记录当前近30天询价数(JSON格式)
            dataAnalysis.setVisitorsByMonthOfJson(JSON.toJSONString(Json));
            //新建统计信息
            dataAnalysisDao.insertDataAnalysisbyVisitors(dataAnalysis);
            activeSessions++;
        }
    }
        //session销毁时执行
        public void sessionDestroyed (HttpSessionEvent se){
            if (activeSessions > 0) {
                activeSessions--;
            }
            //删除sessionId
            HttpSession session = se.getSession();
            UnRepeatLogin.getUserMap().remove(session.getId());
        }
        //获取活动的session个数(在线人数)
        public static int getActiveSessions () {
            return activeSessions;
        }

}
