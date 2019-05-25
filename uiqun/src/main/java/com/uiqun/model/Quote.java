package com.uiqun.model;

import java.io.Serializable;
import java.util.Date;

public class Quote implements Serializable {
    private Integer qotno; //报价单号
    private Integer rfqno; //询价单号
    private String company; //公司名称
    private String pn; //型号
    private String mfg; //品牌
    private Integer qty; //数量
    private String dtcd; //批次
    private Integer qlty; //质量标准
    private Float price; //单价
    private String dtime; //交货日期
    private Integer sure; //是否实价
    private Date qdate; //报价日期
    private Integer isOpen; //是否公开
    private Integer uid; //报价者
    private String qltyName;//质量标准
    private Rfq rfq;//对应报价

    public Integer getQotno() {
        return qotno;
    }

    public void setQotno(Integer qotno) {
        this.qotno = qotno;
    }

    public Integer getRfqno() {
        return rfqno;
    }

    public void setRfqno(Integer rfqno) {
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

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getDtcd() {
        return dtcd;
    }

    public void setDtcd(String dtcd) {
        this.dtcd = dtcd;
    }

    public Integer getQlty() {
        return qlty;
    }

    public void setQlty(Integer qlty) {
        this.qlty = qlty;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public Integer getSure() {
        return sure;
    }

    public void setSure(Integer sure) {
        this.sure = sure;
    }

    public Date getQdate() {
        return qdate;
    }

    public void setQdate(Date qdate) {
        this.qdate = qdate;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getQltyName() {
        return qltyName;
    }

    public void setQltyName(String qltyName) {
        this.qltyName = qltyName;
    }

    public Rfq getRfq() {
        return rfq;
    }

    public void setRfq(Rfq rfq) {
        this.rfq = rfq;
    }
}
