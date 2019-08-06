package com.zhongdao.gather.service.user.dao;

import com.zhongdao.gather.bean.user.SysUser;
import com.zhongdao.gather.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserDaoService {

    @Autowired
    SysUserMapper sysUserMapper;
    /**
     * 根据手机号和密码查询用户
     * @param phone
     * @return
     */
    public SysUser selectByPhone(String phone,String passWord){
       // SysUser sysUser =   sysUserMapper.selectByPrimaryKey(1);
        SysUser sysUser =   sysUserMapper.selectByPhone(phone);
        if(sysUser==null || !sysUser.getPassWord().equals(passWord)){
            return null;
        }
        return sysUser;
    }


    /**
     * 根据手机号查询用户
     * @param phone
     * @return
     */
    public SysUser selectByPhone(String phone){
        // SysUser sysUser =   sysUserMapper.selectByPrimaryKey(1);

        return  sysUserMapper.selectByPhone(phone);
    }
    /**
     * 根据用户ID查询用户
     * @param userId
     * @return
     */
    public SysUser selectByUserId(Integer userId){
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    /**
     * 根据微信openid查询用户
     * @param openId
     * @return
     */

    public SysUser selectByOpenId(String openId){
        return sysUserMapper.selectByOpenId(openId);
    }

    /**
     * 新增一个用户
     * @param sysUser
     * @return
     */
    public int insertUser(SysUser sysUser){
        return  sysUserMapper.insert(sysUser);
    }


    /**
     * 更新用戶
     */
    public int updateSysUser(SysUser sysUser){
        return   sysUserMapper.updateByPrimaryKey(sysUser);
    }

    /**
     * 删除用户
     * @param sysUser
     * @return
     */
    public int deleteUser(SysUser sysUser){

        return  sysUserMapper.deleteByPrimaryKey(sysUser.getUserId());
    }


}
