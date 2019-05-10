package com.uiqun.model;

import java.util.Date;

public class Bom {
    private int bid; //料表编号
    private String company;//公司名称
    private String bname;//料表名称
    private int btype;//料表类型
    private int buse;//料表月用量
    private int isauto;//是否自动匹配
    private String bpn;//型号
    private String bdes;//描述
    private String bmfg;//品牌
    private int bqty;//单机用量
    private Date bdate;//上传日期
    private int uid; //发布者

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public int getBtype() {
        return btype;
    }

    public void setBtype(int btype) {
        this.btype = btype;
    }

    public int getBuse() {
        return buse;
    }

    public void setBuse(int buse) {
        this.buse = buse;
    }

    public int getIsauto() {
        return isauto;
    }

    public void setIsauto(int isauto) {
        this.isauto = isauto;
    }

    public String getBpn() {
        return bpn;
    }

    public void setBpn(String bpn) {
        this.bpn = bpn;
    }

    public String getBdes() {
        return bdes;
    }

    public void setBdes(String bdes) {
        this.bdes = bdes;
    }

    public String getBmfg() {
        return bmfg;
    }

    public void setBmfg(String bmfg) {
        this.bmfg = bmfg;
    }

    public int getBqty() {
        return bqty;
    }

    public void setBqty(int bqty) {
        this.bqty = bqty;
    }

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
