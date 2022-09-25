package com.dragon.server.db.mapper;

import com.dragon.common.base.db.entity.UserInfo;

/**
   *@author Li Dongyang
   *@date 2022/9/24 22:38
*/
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    UserInfo selectByUniqueKey(String openId);
}