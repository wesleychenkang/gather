package com.zhongdao.gather.control.user.entity;

import com.zhongdao.gather.bean.common.BaseResult;
import com.zhongdao.gather.bean.user.SysUserInfo;

public class UserInfoResult extends BaseResult<SysUserInfo>{

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
