package com.uiqun.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pager<T> implements Serializable {
    private int currentPage=1;//当前页
    private int totalCount;//总记录数
    private int totalPage;//总页数
    private int pageSize=10;//每页行数
    private List<T> datas;//每页数据
    private Map<String,Object> condition = new HashMap<String, Object>();
    private int pageOffset;//每页起始页

    public Map<String, Object> getCondition() {
        return condition;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public int getPageOffset() {
        return (currentPage -1)*pageSize;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return (totalCount+ pageSize -1)/pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
