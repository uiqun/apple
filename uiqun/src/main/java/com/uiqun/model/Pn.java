package com.uiqun.model;

public class Pn {
    private Integer pid; //型号编码
    private String ptype; //型号分类
    private String pn; //型号
    private String des; //描述
    private String mfg; //厂牌
    private String pkge; //封装
    private String pack; //包装方式
    private Integer mpq; //最小包装
    private Float price; //单价
    private String speck; //规格
    private String datalink; //规格书链接
    private String dtime;//交期

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
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

    public String getPkge() {
        return pkge;
    }

    public void setPkge(String pkge) {
        this.pkge = pkge;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public Integer getMpq() {
        return mpq;
    }

    public void setMpq(Integer mpq) {
        this.mpq = mpq;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getSpeck() {
        return speck;
    }

    public void setSpeck(String speck) {
        this.speck = speck;
    }

    public String getDatalink() {
        return datalink;
    }

    public void setDatalink(String datalink) {
        this.datalink = datalink;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }
}
