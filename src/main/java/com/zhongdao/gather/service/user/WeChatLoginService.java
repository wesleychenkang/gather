package com.zhongdao.gather.service.user;

import com.zhongdao.gather.bean.user.SysUser;
import com.zhongdao.gather.control.user.entity.WeiXinLoginResult;
import com.zhongdao.gather.service.user.entity.WeChatAccessToken;
import com.zhongdao.gather.service.user.entity.WeChatUser;
import com.zhongdao.gather.utils.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 微信登录相关业务
 */
@Service
public class WeChatLoginService {

    @Autowired
    private WeChatService weChatService;
    @Autowired
    private SysUserService sysUserService;

    public WeiXinLoginResult loginByWeChatCode(String code){
        WeiXinLoginResult result = new WeiXinLoginResult();
        WeChatAccessToken weChatUser =  weChatService.getAccess_token(code);
       // result.setDetail(weChatUser);
        if(weChatUser !=null){
            SysUser sysUser =sysUserService.selectByOpenId(weChatUser.getOpenid());
            if(null == sysUser){
                WeChatUser user =   weChatService.getWeChatUserInfo(weChatUser.getAccess_token(),code);
                if(null!=user) {
                    int userId = sysUserService.insertSysUser(sysUser);
                    result.setUserId(userId+"");
                    //再保存用戶的其它信息
                    String token = CommonMethod.createToken(userId);
                    result.setToken(token);
                    result.setDetail(user);
                }else{
                    result.setDetail(user);
                    result.setSuccess(false);
                    result.setMsg("getWeChat info error");
                }
            }else{
                //老用户执行老用户的业务逻辑
                WeChatUser user = new WeChatUser();
                //

            }

        }else{
            result.setSuccess(false);
            result.setMsg("code fail");
        }
     return  result;
    }


}
