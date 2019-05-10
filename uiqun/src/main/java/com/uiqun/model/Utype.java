package com.uiqun.model;

public class Utype {
    private int id; //用户类别编码
    private String utypeName; //用户类别名称
    private int parentId; //父级编码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUtypeName() {
        return utypeName;
    }

    public void setUtypeName(String utypeName) {
        this.utypeName = utypeName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
