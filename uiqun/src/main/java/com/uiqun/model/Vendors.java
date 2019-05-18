package com.uiqun.model;

import java.io.Serializable;
import java.util.List;

public class Vendors implements Serializable {
    private int id; //用户类别编码
    private String utypeName; //用户类别名称
    private int parentId; //父级编码
    private List<User> user;

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

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
