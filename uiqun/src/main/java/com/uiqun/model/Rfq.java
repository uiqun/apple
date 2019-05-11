package com.uiqun.model;


import java.io.Serializable;
import java.util.Date;

public class Rfq implements Serializable {

  private int rfqno; //询价单号
  private String company; //公司名称
  private int pntype;//型号类型
  private String pn; //型号
  private String mfg; //品牌
  private Integer qty; //数量
  private int qlty; //质量标准Id
  private String dtcd;//批次
  private Double tp; //目标价格
  private String dtime; //交货时间
  private int sure; //是否实单
  private Date rdate; //报价日期
  private int isOpen; //是否公开
  private int uid; //发布者
  private String qltyName; //质量标准

  public String getQltyName() {
    return qltyName;
  }

  public void setQltyName(String qltyName) {
    this.qltyName = qltyName;
  }

  public String getDtcd() {
    return dtcd;
  }

  public void setDtcd(String dtcd) {
    this.dtcd = dtcd;
  }

  public int getPntype() {
    return pntype;
  }

  public void setPntype(int pntype) {
    this.pntype = pntype;
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

  public Integer getQty() {
    return qty;
  }

  public void setQty(Integer qty) {
    this.qty = qty;
  }

  public int getQlty() {
    return qlty;
  }

  public void setQlty(int qlty) {
    this.qlty = qlty;
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

  public int getSure() {
    return sure;
  }

  public void setSure(int sure) {
    this.sure = sure;
  }

  public Date getRdate() {
    return rdate;
  }

  public void setRdate(Date rdate) {
    this.rdate = rdate;
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
