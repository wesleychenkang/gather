package com.zhongdao.gather.control.user.entity;

import com.zhongdao.gather.bean.common.BaseReuqest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserLognRequest extends BaseReuqest {

     @ApiModelProperty(name = "phone",value = "手机号码")
     private String phone;

     @ApiModelProperty(name = "passWord",value = "密码")
     private String passWord;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
