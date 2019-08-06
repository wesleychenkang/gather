package com.zhongdao.gather.bean.user;

public class SysUserInfo {

    private Integer infoId;

    private Integer userId;

    /**
     * 爱好
     */
    private String hobby;

    /**
     * 个人简介
     */
    private String message;

    /**
    * 城市
    */
    private String city;

    /**
    * 收货地址
    */
    private String address;

    /**
    * 国家
    */
    private String country;

    /**
    * 省份
    */
    private String province;

    /**
     * 性別
     */
    private Integer sex;

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}