package com.zhongdao.gather.service.user.dao;

import com.zhongdao.gather.bean.user.UserToken;
import com.zhongdao.gather.mapper.UserTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTokenDaoService {

    @Autowired
    private UserTokenMapper userTokenMapper;

    public UserToken selectByUserId(Integer userId){
        return userTokenMapper.selectByUserId(userId);
    }
    public int insertUserToken(UserToken userToken){
     return  userTokenMapper.insert(userToken);
    }
    public  int updateUserToken(UserToken userToken){

        return  userTokenMapper.updateByPrimaryKey(userToken);
    }
    public int deleteUserToken(Integer tid){
        return userTokenMapper.deleteByPrimaryKey(tid);

    }



}
