package com.zhongdao.gather.service.user.dao;

import com.zhongdao.gather.bean.user.SysUserInfo;
import com.zhongdao.gather.mapper.SysUserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserInfoDaoService {

    @Autowired
    private SysUserInfoMapper sysUserInfoMapper;


    public SysUserInfo selectUserInfoByUserId(Integer userId){

        return sysUserInfoMapper.selectByUserId(userId);
    }

    public int updateUserInfoByUserId(SysUserInfo sysUserInfo){

        return sysUserInfoMapper.updateByPrimaryKey(sysUserInfo);
    }

    public int insertUserInfoByUserId(SysUserInfo sysUserInfo){

        return sysUserInfoMapper.insert(sysUserInfo);
    }

}
