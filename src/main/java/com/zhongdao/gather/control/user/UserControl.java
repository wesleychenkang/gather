package com.zhongdao.gather.control.user;

import com.zhongdao.gather.bean.user.SysUser;
import com.zhongdao.gather.bean.user.UserToken;
import com.zhongdao.gather.control.user.entity.*;
import com.zhongdao.gather.service.user.SysPhoneSMSService;
import com.zhongdao.gather.service.user.SysUserService;
import com.zhongdao.gather.service.user.SysUserTokenService;
import com.zhongdao.gather.utils.CommonMethod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("用户登录相关")
@RestController
@RequestMapping("user")
public class UserControl {

    @Autowired
    private SysUserService sysUseService;

    @Autowired
    private SysPhoneSMSService sysPhoneSMSService;

    @Autowired
    private SysUserTokenService sysUserTokenService;


    @ApiOperation(value = "登录接口",httpMethod = "POST")
    @ApiResponse(message = "test",code = 400)
    @RequestMapping("/login.do")
    public UserLoginResult login(@RequestBody @ApiParam(value = "登录信息")UserLognRequest userLognRequest){
        UserLoginResult result = new UserLoginResult();
        SysUser user =  sysUseService.selectByPhone(userLognRequest.getPhone(),userLognRequest.getPassWord());
        if(user==null){
            result.setToken("login fails");
            result.setMsg("login fails");
            return result;
        }
        //获取token信息
       // result.setDetail(user);
        UserToken userToken = sysUserTokenService.selectByUserId(user.getUserId());
        userToken.setToken(CommonMethod.createToken(user.getUserId()));
        sysUserTokenService.updateUserToken(userToken);
        result.setToken(userToken.getToken());
        return result;
    }


    @ApiOperation(value = "手机登录接口",httpMethod = "POST")
    @ApiResponse(message = "test",code = 400)
    @RequestMapping("/loginByPhone.do")
    public UserLoginResult loginByPhone(@RequestBody @ApiParam(value = "登录信息")UserLoginPhoneRequest userLognRequest){
        UserLoginResult result = new UserLoginResult();
        SysUser user =  sysUseService.selectByPhone(userLognRequest.getPhone());
        if(null == user){
            result.setMsg("用戶不存在");
            result.setSuccess(false);
            return result;
        }
        // 通过手机号码查询到验证
        String code = sysPhoneSMSService.getCodeFromRedis(userLognRequest.getPhone());
        if(code!=null){
            UserToken userToken = sysUserTokenService.selectByUserId(user.getUserId());
            userToken.setToken(CommonMethod.createToken(user.getUserId()));
            sysUserTokenService.updateUserToken(userToken);
            result.setToken(userToken.getToken());
            //result.setDetail(user);
            result.setToken(userToken.getToken());
        }
        return result;
    }



    @ApiOperation(value ="手机注册接口" ,httpMethod = "POST")
    @RequestMapping("register.do")
    public UserLoginResult register( @RequestBody @ApiParam(value = "登录信息")@Validated  UserLoginPhoneRequest userLognRequest){
        UserLoginResult result = new UserLoginResult();
        SysUser user =  sysUseService.selectByPhone(userLognRequest.getPhone());
        if(null != user){
            result.setMsg("用戶已经存在");
            result.setSuccess(false);
            return result;
        }

        // 通过手机号码获取验证码
//        String code = sysPhoneSMSService.getCodeFromRedis(userLognRequest.getPhone());
//        if(!code.equals(userLognRequest.getCode())){
//            result.setDetail(user);
//            result.setMsg("验证码校验失败，请重新获取");
//            result.setSuccess(false);
//            return result;
//        }
        user = new SysUser();
        user.setPhone(userLognRequest.getPhone());
        int r =  sysUseService.insertSysUser(user);
        if(r==1){
            user.setPassWord("");
            result.setSuccess(true);
            UserToken userToken = sysUserTokenService.selectByUserId(user.getUserId());
            userToken.setToken(CommonMethod.createToken(user.getUserId()));
            sysUserTokenService.updateUserToken(userToken);
            result.setToken(userToken.getToken());
        }else{
            result.setToken("fail");
            result.setSuccess(false);
        }
        return result;

    }

    @ApiOperation(value ="获取短信接口" ,httpMethod = "POST")
    @RequestMapping("getSms.do")
    public String getSms(@RequestBody @ApiParam(value = "用户信息")UserLoginPhoneRequest userLognRequest){
        // 通过手机号码查询验证码
        String code = sysPhoneSMSService.getSmsCode(userLognRequest.getPhone());
        return code;

    }

    @ApiOperation(value="用户修改密码" ,httpMethod = "POST")
    @RequestMapping("updatePassWord.do")
    public UserLoginResult updatePassWord(@RequestBody @ApiParam(value = "登录信息") UserUpdateRequest userUpdateRequest){
        UserLoginResult result = new UserLoginResult();
        SysUser sysUser = sysUseService.selectByPhone(userUpdateRequest.getPhone(),userUpdateRequest.getPassWord());
        if(null==sysUser){
            result.setMsg("update fail");
            return result;
        }
        sysUser.setPassWord(userUpdateRequest.getnPassWord());
        int p = sysUseService.updateSysUser(sysUser);
            System.out.println("p===" + p);
        if(p> 0){
            UserToken userToken = sysUserTokenService.selectByUserId(sysUser.getUserId());
            userToken.setToken(CommonMethod.createToken(sysUser.getUserId()));
            sysUserTokenService.updateUserToken(userToken);
            result.setToken(userToken.getToken());
            result.setSuccess(true);
            result.setMsg("success");
        }
        return  result;
    }







}
