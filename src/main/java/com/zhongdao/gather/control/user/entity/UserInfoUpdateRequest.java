package com.zhongdao.gather.control.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserInfoUpdateRequest extends UserInfoRequest {

    /**
     * 爱好
     */
    @ApiModelProperty("爱好")
    private String hobby;

    /**
     * 个人简介
     */
    @ApiModelProperty("个人简介")
    private String message;

    /**
     * 城市
     */
    @ApiModelProperty("城市")
    private String city;

    /**
     * 收货地址
     */
    @ApiModelProperty("收货地址")
    private String address;

    /**
     * 国家
     */
    @ApiModelProperty("国家")
    private String country;

    /**
     * 省份
     */
    @ApiModelProperty("省份")
    private String province;

    /**
     * 性別
     */
    @ApiModelProperty("性别")
    private Integer sex;

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
