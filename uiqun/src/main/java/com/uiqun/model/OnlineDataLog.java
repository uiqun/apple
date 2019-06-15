package com.uiqun.model;

import java.io.Serializable;

/**
 * 在线人数数据统计类
 */
public class OnlineDataLog implements Serializable {
    private Integer today;//今日在线人数
    private Integer nearly30Days;//最近30天在线
    private Integer history;//历史在线人数

    public Integer getToday() {
        return today;
    }

    public void setToday(Integer today) {
        this.today = today;
    }

    public Integer getNearly30Days() {
        return nearly30Days;
    }

    public void setNearly30Days(Integer nearly30Days) {
        this.nearly30Days = nearly30Days;
    }

    public Integer getHistory() {
        return history;
    }

    public void setHistory(Integer history) {
        this.history = history;
    }
}
