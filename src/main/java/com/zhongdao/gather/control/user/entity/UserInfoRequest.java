package com.zhongdao.gather.control.user.entity;

import com.zhongdao.gather.bean.common.BaseReuqest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserInfoRequest extends BaseReuqest {

     @ApiModelProperty(name = "token" ,value = "登录token")
    private  String token;

    @ApiModelProperty(name = "userId" ,value = "用户ID")
    private String userId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
