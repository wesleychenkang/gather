package com.zhongdao.gather.service.user;

import com.zhongdao.gather.bean.user.SysUser;
import com.zhongdao.gather.service.user.dao.SysUserDaoService;
import com.zhongdao.gather.utils.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用戶访问的中间层
 */
@Service
public class SysUserService {

    @Autowired
    private SysUserDaoService sysUserDaoService;


    public SysUser selectByPhone(String phone, String passWord){

        return sysUserDaoService.selectByPhone(phone,passWord);
    }

    public SysUser selectByUserId(Integer userId){

        return sysUserDaoService.selectByUserId(userId);
    }

    public SysUser selectByOpenId(String openId){

        return sysUserDaoService.selectByOpenId(openId);
    }
    public SysUser selectByPhone(String phone){

        return sysUserDaoService.selectByPhone(phone);
    }

    public int insertSysUser(SysUser user){
        user.setPassWord(CommonMethod.createToken(12345));
        user.setSalt(CommonMethod.createSalt());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setLockFlag("0");
        user.setDelFlag("0");
       return sysUserDaoService.insertUser(user);
    }

    public int updateSysUser(SysUser user){
          user.setUpdateTime(new Date());
        return sysUserDaoService.updateSysUser(user);
    }

    public int deleteUser(SysUser user){

        return sysUserDaoService.deleteUser(user);
    }

}
