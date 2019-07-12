package com.uiqun.controller;

import com.alibaba.fastjson.JSON;
import com.uiqun.constant.DataAnalysisConstant;
import com.uiqun.model.DataAnalysis;
import com.uiqun.model.DataAnalysisbyVisitors;
import com.uiqun.service.DataAnalysisService;
import com.uiqun.service.HotstkService;
import com.uiqun.service.PntypeService;
import com.uiqun.service.RfqService;
import com.uiqun.utils.SessionCounter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DataAnalysisController {
    @Resource
    private HotstkService hotstkService;
    @Resource
    private RfqService rfqService;
    @Resource
    private PntypeService pntypeService;
    @Resource
    private DataAnalysisService dataAnalysisService;
    @RequestMapping("/Xanalysis")
    public String Xuser(Model model){
        //今日在线人数
        model.addAttribute("online", SessionCounter.getActiveSessions());
        //近30天的访客数
        model.addAttribute("visitors", dataAnalysisService.queryDataAnalysisbyVisitors());
        //询价前50名
        model.addAttribute("rfq50",dataAnalysisService.getRankingList(DataAnalysisConstant.RANKING,DataAnalysisConstant.RFQ_TYPE));
        //报价前50名
        model.addAttribute("quote50",dataAnalysisService.getRankingList(DataAnalysisConstant.RANKING,DataAnalysisConstant.QUOTE_TYPE));
        return "Xanalysis";
    }

    /**
     * 偏移统计信息指针，修改统计信息
     */
    @Scheduled(cron = "0 0 0 * * ?")
    private void skewingPointerByData(){
        int pageNum =0;
        List<DataAnalysis> dataList = dataAnalysisService.getDataList(pageNum, pageNum + 20);
        while(dataList!=null){
            for (int i = 0; i < dataList.size(); i++) {
                DataAnalysis dataAnalysis = dataList.get(i);
                //修改天数计数器
                Integer dayCount = dataAnalysis.getDayCount();
                dayCount++;
                if(dayCount<30){
                    dataAnalysis.setDayCount(dayCount);
                }else{
                    dataAnalysis.setDayCount(0);
                }
                //修改近30天数据(JSON类型)
                ArrayList<Integer> arrayList = JSON.parseObject(dataAnalysis.getCountByMonthOfJson(), ArrayList.class);
                arrayList.set(dayCount,0);
                dataAnalysis.setCountByMonthOfJson(JSON.toJSONString(arrayList));
                //修改近30天数据(Integer类型)
                int count = 0;
                for (int j = 0; j < arrayList.size(); j++) {
                    count+=arrayList.get(j);
                }
                dataAnalysis.setCountByMonth(count);
            }
            pageNum=pageNum + 20;
            dataList = dataAnalysisService.getDataList(pageNum, pageNum + 20);
        }
    }

    //偏移统计信息指针，修改统计信息(访客数)
    @Scheduled(cron = "0 0 0 * * ?")
    private void skewingPointerByVisitors(){
        DataAnalysisbyVisitors dataAnalysisbyVisitors = dataAnalysisService.queryDataAnalysisbyVisitors();
        if(dataAnalysisbyVisitors!=null){
            //修改天数计数器
            Integer dayCount = dataAnalysisbyVisitors.getDayCount();
            dayCount++;
            if(dayCount<30){
                dataAnalysisbyVisitors.setDayCount(dayCount);
            }else{
                dataAnalysisbyVisitors.setDayCount(0);
            }
            //修改近30天数据(JSON类型)
            ArrayList<Integer> arrayList = JSON.parseObject(dataAnalysisbyVisitors.getVisitorsByMonthOfJson(), ArrayList.class);
            arrayList.set(dayCount,0);
            dataAnalysisbyVisitors.setVisitorsByMonthOfJson(JSON.toJSONString(arrayList));
            //修改近30天数据(Integer类型)
            int count = 0;
            for (int j = 0; j < arrayList.size(); j++) {
                count+=arrayList.get(j);
            }
            dataAnalysisbyVisitors.setVisitorsByMonth(count);
        }
    }

}
