package com.uiqun.model;

import java.util.Date;

public class Quote {
    private int qotno; //报价单号
    private int rfqno; //询价单号
    private String company; //公司名称
    private String pn; //型号
    private String mfg; //品牌
    private int qty; //数量
    private String dtcd; //批次
    private int qlty; //质量标准
    private float price; //单价
    private String dtime; //交货日期
    private int sure; //是否实价
    private Date date; //报价日期
    private int isOpen; //是否公开
    private int uid; //报价者

    public int getQotno() {
        return qotno;
    }

    public void setQotno(int qotno) {
        this.qotno = qotno;
    }

    public int getRfqno() {
        return rfqno;
    }

    public void setRfqno(int rfqno) {
        this.rfqno = rfqno;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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

    public int getSure() {
        return sure;
    }

    public void setSure(int sure) {
        this.sure = sure;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
