package com.zhongdao.gather.mapper;

import com.zhongdao.gather.bean.user.SysUserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserInfoMapper {

    int deleteByPrimaryKey(Integer infoId);

    int insert(SysUserInfo record);

    int insertSelective(SysUserInfo record);

    SysUserInfo selectByPrimaryKey(Integer infoId);

    int updateByPrimaryKeySelective(SysUserInfo record);

    int updateByPrimaryKey(SysUserInfo record);

    SysUserInfo selectByUserId(Integer userId);
}