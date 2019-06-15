package com.uiqun.model;

import java.util.Date;

/**
 * 报价数据统计类
 */
public class QuoteDataLog {
    private String quotePn;//报价型号
    private Date startDayByQuote;//从那天开始被报价
    private Date restartDayByQuote;//从那天开始重新被报价
    private Date endDayByQuote;//从那天开始 没有 被报价(即30天后,型号未被报价则设置该值)
    private Integer todayQueryByQuote;//本日被报价
    private Integer sumQueryByQuote;//共被报价

    public String getQuotePn() {
        return quotePn;
    }

    public void setQuotePn(String quotePn) {
        this.quotePn = quotePn;
    }


    public Date getStartDayByQuote() {
        return startDayByQuote;
    }

    public void setStartDayByQuote(Date startDayByQuote) {
        this.startDayByQuote = startDayByQuote;
    }

    public Date getRestartDayByQuote() {
        return restartDayByQuote;
    }

    public void setRestartDayByQuote(Date restartDayByQuote) {
        this.restartDayByQuote = restartDayByQuote;
    }

    public Date getEndDayByQuote() {
        return endDayByQuote;
    }

    public void setEndDayByQuote(Date endDayByQuote) {
        this.endDayByQuote = endDayByQuote;
    }

    public Integer getTodayQueryByQuote() {
        return todayQueryByQuote;
    }

    public void setTodayQueryByQuote(Integer todayQueryByQuote) {
        this.todayQueryByQuote = todayQueryByQuote;
    }

    public Integer getSumQueryByQuote() {
        return sumQueryByQuote;
    }

    public void setSumQueryByQuote(Integer sumQueryByQuote) {
        this.sumQueryByQuote = sumQueryByQuote;
    }
}
