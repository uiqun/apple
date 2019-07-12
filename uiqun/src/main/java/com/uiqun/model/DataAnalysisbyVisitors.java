package com.uiqun.model;


import java.io.Serializable;

public class DataAnalysisbyVisitors implements Serializable {

    private Integer dayCount;
    private Integer visitorsByMonth;
    private String visitorsByMonthOfJson;
    private Integer historicalVisitors;


    public Integer getDayCount() {
        return dayCount;
    }

    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
    }

    public Integer getVisitorsByMonth() {
        return visitorsByMonth;
    }

    public void setVisitorsByMonth(Integer visitorsByMonth) {
        this.visitorsByMonth = visitorsByMonth;
    }

    public String getVisitorsByMonthOfJson() {
        return visitorsByMonthOfJson;
    }

    public void setVisitorsByMonthOfJson(String visitorsByMonthOfJson) {
        this.visitorsByMonthOfJson = visitorsByMonthOfJson;
    }

    public Integer getHistoricalVisitors() {
        return historicalVisitors;
    }

    public void setHistoricalVisitors(Integer historicalVisitors) {
        this.historicalVisitors = historicalVisitors;
    }
}
