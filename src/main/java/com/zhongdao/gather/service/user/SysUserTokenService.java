package com.zhongdao.gather.service.user;

import com.zhongdao.gather.bean.user.UserToken;
import com.zhongdao.gather.service.user.dao.UserTokenDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserTokenService {

    @Autowired
    private UserTokenDaoService sysUserTokenService;


    public UserToken selectByUserId(Integer userId){
        return sysUserTokenService.selectByUserId(userId);
    }
    public int insertUserToken(UserToken userToken){
        return  sysUserTokenService.insertUserToken(userToken);
    }
    public  int updateUserToken(UserToken userToken){
        userToken.setTime(System.currentTimeMillis());
        return  sysUserTokenService.updateUserToken(userToken);
    }
    public int deleteUserToken(Integer tid){
        return sysUserTokenService.deleteUserToken(tid);
    }
}
