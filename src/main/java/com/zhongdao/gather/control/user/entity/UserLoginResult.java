package com.zhongdao.gather.control.user.entity;

import com.zhongdao.gather.bean.common.BaseResult;
import com.zhongdao.gather.bean.user.SysUser;

public class UserLoginResult extends BaseResult<SysUser> {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
