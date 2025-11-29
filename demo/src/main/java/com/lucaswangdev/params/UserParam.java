package com.lucaswangdev.params;

import com.lucaswangdev.common.BaseParam;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

@Alias("UserParam")
public class UserParam extends BaseParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     * type BIGINT
     */
    private Long id;
    /**
     * 创建时间
     * type DATETIME
     */
    private java.util.Date gmtCreate;
    /**
     * 更新时间
     * type DATETIME
     */
    private java.util.Date gmtModified;
    /**
     * 用户名
     * type VARCHAR
     */
    private String userName;
    /**
     * 性别
     * type VARCHAR
     */
    private String sex;
    /**
     * 地址
     * type VARCHAR
     */
    private String address;
  
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public java.util.Date getGmtCreate() {
        return gmtCreate;
    }

    @Override
    public void setGmtCreate(java.util.Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    public java.util.Date getGmtModified() {
        return gmtModified;
    }

    @Override
    public void setGmtModified(java.util.Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

  
}