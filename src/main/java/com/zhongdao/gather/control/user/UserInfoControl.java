package com.zhongdao.gather.control.user;

import com.zhongdao.gather.bean.user.SysUser;
import com.zhongdao.gather.bean.user.SysUserInfo;
import com.zhongdao.gather.bean.user.UserToken;
import com.zhongdao.gather.control.user.entity.UserInfoRequest;
import com.zhongdao.gather.control.user.entity.UserInfoResult;
import com.zhongdao.gather.control.user.entity.UserInfoUpdateRequest;
import com.zhongdao.gather.control.user.entity.UserLoginResult;
import com.zhongdao.gather.service.user.SysUserInfoService;
import com.zhongdao.gather.service.user.SysUserService;
import com.zhongdao.gather.service.user.SysUserTokenService;
import com.zhongdao.gather.utils.CommonMethod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("用户信息相关")
@RestController
@RequestMapping("userinfo")
public class UserInfoControl {

    @Autowired
    private SysUserTokenService sysUserTokenService;

    @Autowired
    private SysUserService sysUseService;

    @Autowired
    private SysUserInfoService sysUserInfoService;


    @ApiOperation(value="获取用户信息" ,httpMethod = "POST")
    @RequestMapping("getUserInfo.do")
    public UserInfoResult getUserInfo(@RequestBody @ApiParam(value = "获取用户信息") UserInfoRequest request){

        return  getUserInfoResult(request);
    }


    @ApiOperation(value="更新用户信息" ,httpMethod = "POST")
    @RequestMapping("updateUserInfo.do")
    public UserInfoResult updateUserInfo(@RequestBody @ApiParam(value = "更新用户信息") UserInfoUpdateRequest request){
        UserInfoResult userInfoResult = getUserInfoResult(request);
        try {
            Integer userId = Integer.parseInt(request.getUserId());
            SysUserInfo sysUserInfo = sysUserInfoService.selectUserInfoByUserId(userId);
            sysUserInfoService.updateUserInfoByUserId(sysUserInfo);
            userInfoResult.setSuccess(true);
        }catch (Exception e){

            userInfoResult.setToken("fail");
            userInfoResult.setSuccess(false);
        }
         return userInfoResult;
    }

    private UserInfoResult getUserInfoResult( UserInfoRequest request){
        UserInfoResult result = new UserInfoResult();
        Integer uId = Integer.parseInt(request.getUserId());
        UserToken userToken = sysUserTokenService.selectByUserId(uId);
        if(userToken == null) {
            result.setToken("");
            result.setMsg("token fail");
            return result;
        }
        //查询用户信息
        SysUser sysUser = sysUseService.selectByUserId(uId);
        if(sysUser==null){
            result.setToken("");
            result.setMsg("token fail");
            return result;
        }
        //查询用户基本信息
        SysUserInfo sysUserInfo =  sysUserInfoService.selectUserInfoByUserId(sysUser.getUserId());
        if(null==sysUserInfo){
            result.setToken("");
            result.setMsg("info fail");
            return result;
        }

        long now = System.currentTimeMillis();
        if(!request.getToken().equals(userToken.getToken()) || (now - Long.valueOf(userToken.getTime())) > 3600 * 1000){
            result.setToken("");
            result.setMsg("token fail");
            return result;
        }
        userToken.setToken(CommonMethod.createToken(uId));
        userToken.setTime(now);
        sysUserTokenService.updateUserToken(userToken);
        result.setToken(userToken.getToken());
        result.setMsg("success");
        result.setSuccess(true);
        result.setDetail(sysUserInfo);
        return  result;
    }








}
