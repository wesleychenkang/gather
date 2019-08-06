package com.zhongdao.gather.bean.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BaseReuqest {

    @ApiModelProperty(name = "timestamp" , value = "时间戳保留秒",example = "15211111111")
    private long timestamp;
    @ApiModelProperty(name = "signsture" , value = "签名值")
    private String signsture;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignsture() {
        return signsture;
    }

    public void setSignsture(String signsture) {
        this.signsture = signsture;
    }
}
