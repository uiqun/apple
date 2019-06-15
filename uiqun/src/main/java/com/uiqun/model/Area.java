package com.uiqun.model;

public class Area {
    private Integer areaId; //区域编号
    private String areaAbbr; //区域缩写
    private String areaName; //区域名称

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaAbbr() {
        return areaAbbr;
    }

    public void setAreaAbbr(String areaAbbr) {
        this.areaAbbr = areaAbbr;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
