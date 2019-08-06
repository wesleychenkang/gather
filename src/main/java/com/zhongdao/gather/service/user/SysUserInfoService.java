package com.zhongdao.gather.service.user;

import com.zhongdao.gather.bean.user.SysUserInfo;
import com.zhongdao.gather.service.user.dao.SysUserInfoDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserInfoService {

    @Autowired
    private SysUserInfoDaoService sysUserDaoService;

    public SysUserInfo selectUserInfoByUserId(Integer userId){

        return sysUserDaoService.selectUserInfoByUserId(userId);
    }

    public int updateUserInfoByUserId(SysUserInfo sysUserInfo){

        return sysUserDaoService.updateUserInfoByUserId(sysUserInfo);
    }

    public int insertUserInfoByUserId(SysUserInfo sysUserInfo){

        return sysUserDaoService.insertUserInfoByUserId(sysUserInfo);
    }


}
