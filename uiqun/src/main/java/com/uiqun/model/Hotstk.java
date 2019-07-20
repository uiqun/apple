package com.uiqun.model;

import java.io.Serializable;
import java.util.Date;

public class Hotstk implements Serializable {

    private String pn; //型号
    private String pntype;//型号类型
    private String mfg; //品牌
    private String pkge; //封装
    private Integer qty; //数量
    private Float price; //单价
    private String dtcd; //批次
    private String qltyName; //质量名称
    private String dtime; //货期
    private String buylink; //购买链接
    private Date hdate; //热卖发布日期
    private Integer uid; //发布者
    private String company; //公司名称
    private String qlty; //质量标准id
    private Integer hid; //库存编号

    public String getPntype() {
        return pntype;
    }

    public void setPntype(String pntype) {
        this.pntype = pntype;
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

    public String getPkge() {
        return pkge;
    }

    public void setPkge(String pkge) {
        this.pkge = pkge;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDtcd() {
        return dtcd;
    }

    public void setDtcd(String dtcd) {
        this.dtcd = dtcd;
    }

    public String getQltyName() {
        return qltyName;
    }

    public void setQltyName(String qltyName) {
        this.qltyName = qltyName;
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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getQlty() {
        return qlty;
    }

    public void setQlty(String qlty) {
        this.qlty = qlty;
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }
}
