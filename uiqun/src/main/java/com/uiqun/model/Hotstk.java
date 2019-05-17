package com.uiqun.model;

import java.io.Serializable;
import java.util.Date;

public class Hotstk implements Serializable {
    private int hid; //库存编号
    private String company; //公司名称
    private String pn; //型号
    private String mfg; //品牌
    private String pack; //封装
    private int qty; //数量
    private float price; //单价
    private String dtcd; //批次
    private int qlty; //质量标准id
    private String dtime; //货期
    private String buylink; //购买链接
    private Date hdate; //热卖发布日期
    private int uid; //发布者
    private String qltyName; //质量标准

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getMfg() {
        return mfg;
    }

    public void setMfg(String mfg) {
        this.mfg = mfg;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDtcd() {
        return dtcd;
    }

    public void setDtcd(String dtcd) {
        this.dtcd = dtcd;
    }

    public int getQlty() {
        return qlty;
    }

    public void setQlty(int qlty) {
        this.qlty = qlty;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public String getBuylink() {
        return buylink;
    }

    public void setBuylink(String buylink) {
        this.buylink = buylink;
    }

    public Date getHdate() {
        return hdate;
    }

    public void setHdate(Date hdate) {
        this.hdate = hdate;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getQltyName() {
        return qltyName;
    }

    public void setQltyName(String qltyName) {
        this.qltyName = qltyName;
    }
}
