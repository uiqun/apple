package com.uiqun.model;


import java.io.Serializable;
import java.util.Date;

public class Rfq implements Serializable {

  private Integer rfqno; //询价单号
  private String company; //公司名称
  private Integer pntype;//型号类型
  private String pn; //型号
  private String mfg; //品牌
  private Integer qty; //数量
  private Integer qlty; //质量标准Id
  private String dtcd;//批次
  private Double tp; //目标价格
  private String dtime; //交货时间
  private Integer sure; //是否实单
  private Date rdate; //报价日期
  private Integer isOpen; //是否公开
  private Integer uid; //发布者
  private String qltyName; //质量标准

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

  public Integer getPntype() {
    return pntype;
  }

  public void setPntype(Integer pntype) {
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

  public Integer getQty() {
    return qty;
  }

  public void setQty(Integer qty) {
    this.qty = qty;
  }

  public Integer getQlty() {
    return qlty;
  }

  public void setQlty(Integer qlty) {
    this.qlty = qlty;
  }

  public String getDtcd() {
    return dtcd;
  }

  public void setDtcd(String dtcd) {
    this.dtcd = dtcd;
  }

  public Double getTp() {
    return tp;
  }

  public void setTp(Double tp) {
    this.tp = tp;
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

  public Date getRdate() {
    return rdate;
  }

  public void setRdate(Date rdate) {
    this.rdate = rdate;
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
}
