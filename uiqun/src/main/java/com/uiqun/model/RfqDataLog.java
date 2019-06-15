package com.uiqun.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 询价数据统计类
 */
public class RfqDataLog implements Serializable {
    private String rfqPn;//询价型号
    private Date startDayByRfq;//从那天开始被查询
    private Date restartDayByRfq;//从那天开始重新被查询
    private Date endDayByRfq;//从那天开始 没有 被查询(即30天后,型号未被查询则设置该值)
    private Integer todayQueryByRfq=0;//本日查询
    private Integer sumQueryByRfq=0;//共查询

    public String getRfqPn() {
        return rfqPn;
    }

    public void setRfqPn(String rfqPn) {
        this.rfqPn = rfqPn;
    }


    public Date getStartDayByRfq() {
        return startDayByRfq;
    }

    public void setStartDayByRfq(Date startDayByRfq) {
        this.startDayByRfq = startDayByRfq;
    }

    public Date getRestartDayByRfq() {
        return restartDayByRfq;
    }

    public void setRestartDayByRfq(Date restartDayByRfq) {
        this.restartDayByRfq = restartDayByRfq;
    }

    public Date getEndDayByRfq() {
        return endDayByRfq;
    }

    public void setEndDayByRfq(Date endDayByRfq) {
        this.endDayByRfq = endDayByRfq;
    }

    public Integer getTodayQueryByRfq() {
        return todayQueryByRfq;
    }

    public void setTodayQueryByRfq(Integer todayQueryByRfq) {
        this.todayQueryByRfq = todayQueryByRfq;
    }

    public Integer getSumQueryByRfq() {
        return sumQueryByRfq;
    }

    public void setSumQueryByRfq(Integer sumQueryByRfq) {
        this.sumQueryByRfq = sumQueryByRfq;
    }
}
