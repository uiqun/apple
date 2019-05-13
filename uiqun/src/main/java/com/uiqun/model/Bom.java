package com.uiqun.model;

public class Bom {
    private int pid; //型号编号
    private int bid; //BOM编号
    private String pn; //型号
    private String des; //描述
    private String mfg; //品牌
    private int usage; //单机用量
    private int mpq; //最小包装
    private float price; //单价
    private String dtime; //货期

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getMfg() {
        return mfg;
    }

    public void setMfg(String mfg) {
        this.mfg = mfg;
    }

    public int getUsage() {
        return usage;
    }

    public void setUsage(int usage) {
        this.usage = usage;
    }

    public int getMpq() {
        return mpq;
    }

    public void setMpq(int mpq) {
        this.mpq = mpq;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }
}
