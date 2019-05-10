package com.travel.common.vo;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("Serializable")
public class PageObject implements Serializable {
    private int pageCurrent;
    private int pageCount;
    private int pageSize;
    private int total;
    private List<Object> records;

    public int getPageCurrent() {
        return pageCurrent;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setPageCurrent(int pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Object> getRecords() {
        return records;
    }

    public void setRecords(List<Object> records) {
        this.records = records;
    }


}
