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
        List<DataAnalysis> rankingList = dataAnalysisService.getRankingList(DataAnalysisConstant.RANKING, DataAnalysisConstant.RFQ_TYPE);
        for (int i = 0; i < rankingList.size(); i++){
            if(rankingList.get(i).getCountByMonth()==0){
                rankingList.remove(rankingList.get(i));
            }
        }
        model.addAttribute("rfq50",rankingList);
        //报价前50名
        List<DataAnalysis> rankingList1 = dataAnalysisService.getRankingList(DataAnalysisConstant.RANKING, DataAnalysisConstant.QUOTE_TYPE);
        for (int i = 0; i < rankingList1.size(); i++){
            if(rankingList1.get(i).getCountByMonth()==0){
                rankingList1.remove(rankingList1.get(i));
            }
        }
        model.addAttribute("quote50",rankingList1);
        return "Xanalysis";
    }



}
