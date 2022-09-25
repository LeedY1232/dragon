package com.dragon.server.manager;

import javax.annotation.Resource;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import com.dragon.common.base.db.entity.UserInfo;
import com.dragon.server.manager.dto.UserInfoManager;

/**
 * @author Li Dongyang
 * @date 2022/9/24 22:41
 */
@Service
public class UserInfoService {
    @Resource
    private UserInfoManager userInfoManager;

    public UserInfo getByOpenId(String openId){
        return userInfoManager.getByOpenId(openId);
    }

    public UserInfo createUserInfo(String openId, String avatar, String username){
        UserInfo record = new UserInfo();
        record.setWxOpenId(openId);
        record.setAvatar(avatar);
        record.setUsername(username);
        long now = System.currentTimeMillis();
        record.setCreateTime(now);
        record.setModifyTime(now);
        userInfoManager.insert(record);
        return record;
    }
}
