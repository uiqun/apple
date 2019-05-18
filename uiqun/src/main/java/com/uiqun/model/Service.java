package com.uiqun.model;


import java.io.Serializable;
import java.util.Date;

public class Service implements Serializable {

  private Integer sid;//服务id
  private Integer uid;//用户id
  private String sname;//服务名
  private String scontent;//服务内容
  private Date startdate;//开始时间
  private Date enddate;//结束时间
  private Integer fee;//费用

  private String uName;//用户名

  public String getuName() {
    return uName;
  }

  public void setuName(String uName) {
    this.uName = uName;
  }

  public Integer getSid() {
    return sid;
  }

  public void setSid(Integer sid) {
    this.sid = sid;
  }

  public Integer getUid() {
    return uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }

  public String getSname() {
    return sname;
  }

  public void setSname(String sname) {
    this.sname = sname;
  }

  public String getScontent() {
    return scontent;
  }

  public void setScontent(String scontent) {
    this.scontent = scontent;
  }

  public Date getStartdate() {
    return startdate;
  }

  public void setStartdate(Date startdate) {
    this.startdate = startdate;
  }

  public Date getEnddate() {
    return enddate;
  }

  public void setEnddate(Date enddate) {
    this.enddate = enddate;
  }

  public Integer getFee() {
    return fee;
  }

  public void setFee(Integer fee) {
    this.fee = fee;
  }
}
