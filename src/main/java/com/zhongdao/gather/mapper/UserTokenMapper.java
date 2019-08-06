package com.zhongdao.gather.mapper;

import com.zhongdao.gather.bean.user.UserToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTokenMapper {

    int deleteByPrimaryKey(Integer tid);

    int insert(UserToken record);

    int insertSelective(UserToken record);

    UserToken selectByPrimaryKey(Integer tid);

    int updateByPrimaryKeySelective(UserToken record);

    int updateByPrimaryKey(UserToken record);

    UserToken selectByUserId(Integer userId);
}