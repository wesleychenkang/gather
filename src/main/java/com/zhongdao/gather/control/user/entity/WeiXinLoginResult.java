package com.zhongdao.gather.control.user.entity;

import com.zhongdao.gather.bean.common.BaseResult;
import com.zhongdao.gather.service.user.entity.WeChatUser;

public class WeiXinLoginResult extends BaseResult<WeChatUser> {

    private String userId;
    private String weChatToken;
    private String token;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWeChatToken() {
        return weChatToken;
    }

    public void setWeChatToken(String weChatToken) {
        this.weChatToken = weChatToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
