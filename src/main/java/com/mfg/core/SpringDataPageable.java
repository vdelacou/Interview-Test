package com.mfg.core;

/**
 * ^_^
 * Created by gaojiawei on 2016/9/30.
 */ import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

public class SpringDataPageable implements Serializable, Pageable {
    // 当前页
    private Integer pagenumber = 1;
    // 当前页面条数
    private Integer pagesize = 10;
    //排序条件
    private Sort sort;

    public void setSort(Sort sort) {
        this.sort = sort;
    }
    // 当前页面

    public int getPageNumber() {
        return getPagenumber();
    }
    // 每一页显示的条数

    public int getPageSize() {
        return getPagesize();
    }
    // 第二页所需要增加的数量

    public int getOffset() {
        return (getPagenumber() - 1) * getPagesize();
    }

    public Sort getSort() {
        return sort;
    }
    public Integer getPagenumber() {
        return pagenumber;
    }
    public void setPagenumber(Integer pagenumber) {
        this.pagenumber = pagenumber;
    }
    public Integer getPagesize() {
        return pagesize;
    }
    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }
    public Pageable first() {
        // TODO Auto-generated method stub
        return null;
    }
    public boolean hasPrevious() {
        // TODO Auto-generated method stub
        return false;
    }
    public Pageable next() {
        // TODO Auto-generated method stub
        return null;
    }
    public Pageable previousOrFirst() {
        // TODO Auto-generated method stub
        return null;
    }
}