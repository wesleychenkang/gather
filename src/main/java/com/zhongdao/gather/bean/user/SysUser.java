package com.zhongdao.gather.bean.user;

import java.util.Date;

public class SysUser {
    /**
    * 用户id
    */
    private Integer userId;

    /**
    * 用户名
    */
    private String userName;

    /**
    * 随机盐
    */
    private String salt;

    /**
    * 电话号码
    */
    private String phone;

    /**
    * 头像
    */
    private String avatar;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * qq的账号openid
    */
    private String qqOpenid;

    /**
    * 微信openid
    */
    private String wxOpenid;

    /**
    * 0,正常，1.删除
    */
    private String delFlag;

    /**
    * 0正常，9锁定
    */
    private String lockFlag;

    /**
    * 更新时间
    */
    private Date updateTime;

    private String passWord;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getQqOpenid() {
        return qqOpenid;
    }

    public void setQqOpenid(String qqOpenid) {
        this.qqOpenid = qqOpenid;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(String lockFlag) {
        this.lockFlag = lockFlag;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}