package com.zhongdao.gather.control.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserUpdateRequest extends UserLognRequest {

    @ApiModelProperty
    private String nPassWord;

    public String getnPassWord() {
        return nPassWord;
    }

    public void setnPassWord(String nPassWord) {
        this.nPassWord = nPassWord;
    }
}
