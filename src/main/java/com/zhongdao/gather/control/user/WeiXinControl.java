package com.zhongdao.gather.control.user;

import com.zhongdao.gather.control.user.entity.WeiXinLoginResult;
import com.zhongdao.gather.service.user.WeChatLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("微信相关")
@RestController
@RequestMapping("weixin")
public class WeiXinControl {

    @Autowired
    private WeChatLoginService loginService;

    @ApiOperation(value = "微信获取Access_token",httpMethod = "POST")
    @ApiResponse(message = "test",code = 400)
    @RequestMapping("/getAccess_token.do")
    public WeiXinLoginResult getAccess_token(@RequestBody @ApiParam(value = "用户信息")String code){
        System.out.println("====="+code);
      return loginService.loginByWeChatCode(code) ;
    }


    /**
     * 分享
     */
    public void share(){

    }

    /**
     * 邀请
     */
    public void invite(){



    }


}
