package com.lucaswangdev.common;

import java.util.Date;
import java.util.List;

/**
 * 数据库查询条件基类
 */
public class BaseParam {
    /**
     * 主键id
     */
    protected Long id;

    /**
     * 主键ID列表
     */
    protected List<Long> idList;

    /**
     * 根据主键ID范围查询开始
     */
    protected Long idBegin;

    /**
     * 根据主键ID范围查询结束
     */
    protected Long idEnd;

    /**
     * 创建时间
     */
    protected Date gmtCreate;

    /**
     * 创建时间从指定时间开始
     */
    protected Date gmtCreateBegin;

    /**
     * 创建时间从指定时间结束
     */
    protected Date gmtCreateEnd;

    /**
     * 修改时间
     */
    protected Date gmtModified;

    /**
     * 修改时间指定时间开始
     */
    protected Date gmtModifiedBegin;

    /**
     * 修改时间指定时间结束
     */
    protected Date gmtModifiedEnd;

    /**
     * limit语句offset
     */
    protected Long offset;

    /**
     * limit语句size
     */
    protected Long size;

    /**
     * 排序方式
     */
    protected List<String> orderByList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public Long getIdBegin() {
        return idBegin;
    }

    public void setIdBegin(Long idBegin) {
        this.idBegin = idBegin;
    }

    public Long getIdEnd() {
        return idEnd;
    }

    public void setIdEnd(Long idEnd) {
        this.idEnd = idEnd;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtCreateBegin() {
        return gmtCreateBegin;
    }

    public void setGmtCreateBegin(Date gmtCreateBegin) {
        this.gmtCreateBegin = gmtCreateBegin;
    }

    public Date getGmtCreateEnd() {
        return gmtCreateEnd;
    }

    public void setGmtCreateEnd(Date gmtCreateEnd) {
        this.gmtCreateEnd = gmtCreateEnd;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtModifiedBegin() {
        return gmtModifiedBegin;
    }

    public void setGmtModifiedBegin(Date gmtModifiedBegin) {
        this.gmtModifiedBegin = gmtModifiedBegin;
    }

    public Date getGmtModifiedEnd() {
        return gmtModifiedEnd;
    }

    public void setGmtModifiedEnd(Date gmtModifiedEnd) {
        this.gmtModifiedEnd = gmtModifiedEnd;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public List<String> getOrderByList() {
        return orderByList;
    }

    public void setOrderByList(List<String> orderByList) {
        this.orderByList = orderByList;
    }
}
