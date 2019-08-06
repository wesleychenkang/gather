package com.zhongdao.gather.control.account;

import com.zhongdao.gather.control.account.entity.AccountRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("用户账号相关")
@RestController
@RequestMapping("account")
public class AccountControl {


    @ApiOperation(value = "账号信息获取接口",httpMethod = "POST")
    @ApiResponse(message = "test",code = 400)
    @RequestMapping("/getAccountInfo.do")
    public String getAccountInfo(@RequestBody @ApiParam(value = "账号信息")AccountRequest accountRequest){



        return "";
    }

    @ApiOperation(value = "使用代金券接口",httpMethod = "POST")
    @ApiResponse(message = "test",code = 400)
    @RequestMapping("/useAccountVoucher.do")
    public void  useAccountVoucher(@RequestBody @ApiParam(value = "代金券信息")String voucher){


    }

    @ApiOperation(value = "使用宝石或者金币接口",httpMethod = "POST")
    @ApiResponse(message = "test",code = 400)
    @RequestMapping("/useAccountGold.do")
    public void  useAccountGold(@RequestBody @ApiParam(value = "代金券信息")String voucher){


    }

    @ApiOperation(value = "使用余额",httpMethod = "POST")
    @ApiResponse(message = "test",code = 400)
    @RequestMapping("/useAccountBalance.do")
    public void  useAccountBalance(){


    }

    @ApiOperation(value = "提现接口",httpMethod = "POST")
    @ApiResponse(message = "test",code = 400)
    @RequestMapping("/useAccountToWeChat.do")
    public void  useAccountToWeChat(){

    }


    @ApiOperation(value = "更新账号宝石信息",httpMethod = "POST")
    @ApiResponse(message = "test",code = 400)
    @RequestMapping("/updateAccountGold.do")
    public void updateAccountGold(){


    }
}
