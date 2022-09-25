package com.dragon.server.manager.dto;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dragon.common.base.db.entity.UserInfo;
import com.dragon.server.db.mapper.UserInfoMapper;

/**
 * @author Li Dongyang
 * @date 2022/9/24 22:38
 */
@Service
public class UserInfoManager {

    @Resource
    private UserInfoMapper userInfoMapper;


    public int deleteByPrimaryKey(Integer id) {
        return userInfoMapper.deleteByPrimaryKey(id);
    }


    public int insert(UserInfo record) {
        return userInfoMapper.insert(record);

    }


    public UserInfo selectByPrimaryKey(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(UserInfo record) {
        return userInfoMapper.updateByPrimaryKeySelective(record);
    }

    public UserInfo getByOpenId(String openId) {
        return userInfoMapper.selectByUniqueKey(openId);
    }
}
