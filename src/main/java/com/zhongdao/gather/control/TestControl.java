package com.zhongdao.gather.control;

import com.zhongdao.gather.config.WeiXinConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("测试使用相关")
@RequestMapping("test")
public class TestControl {
    @Autowired
    private WeiXinConfig config;
    @ApiOperation("all方法")
    @ApiImplicitParam(required = true,name = "test",value = "参数test")
    @RequestMapping("all")
    public String getString(String test){

        return "test"+config.getAppId() + "weix"+ config.getAppSecret();
    }
}
