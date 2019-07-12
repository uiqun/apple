package com.uiqun.model;


import java.io.Serializable;
import java.util.Date;

public class DataAnalysis implements Serializable {

    private Integer id;//编号
    private Integer type;//类型：1.询价 2.报价
    private String pn;//型号
    private Integer dayCount;//查询天数计数器
    private String countByMonthOfJson;//近30天查询数（JSON格式）
    private Integer countByMonth;//近30天的查询数
    private Integer countByHistory;//总查询数
    private Date selLastDate;//最后一次查询时间

    public DataAnalysis() {
    }

    public DataAnalysis(Integer type, String pn) {
        this.type = type;
        this.pn = pn;
    }

    public DataAnalysis(Integer type, String pn, Integer dayCount, String countByMonthOfJson, Integer countByMonth, Integer countByHistory, Date selLastDate) {
        this.type = type;
        this.pn = pn;
        this.dayCount = dayCount;
        this.countByMonthOfJson = countByMonthOfJson;
        this.countByMonth = countByMonth;
        this.countByHistory = countByHistory;
        this.selLastDate = selLastDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public Integer getDayCount() {
        return dayCount;
    }

    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
    }

    public String getCountByMonthOfJson() {
        return countByMonthOfJson;
    }

    public void setCountByMonthOfJson(String countByMonthOfJson) {
        this.countByMonthOfJson = countByMonthOfJson;
    }

    public Integer getCountByMonth() {
        return countByMonth;
    }

    public void setCountByMonth(Integer countByMonth) {
        this.countByMonth = countByMonth;
    }

    public Integer getCountByHistory() {
        return countByHistory;
    }

    public void setCountByHistory(Integer countByHistory) {
        this.countByHistory = countByHistory;
    }

    public Date getSelLastDate() {
        return selLastDate;
    }

    public void setSelLastDate(Date selLastDate) {
        this.selLastDate = selLastDate;
    }
}
