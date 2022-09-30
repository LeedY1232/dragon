package com.dragon.server.util;

import com.dragon.server.db.entity.User;
import com.dragon.server.dto.UserLoginRequest;

/**
 * @author henry
 * @date 2022/9/29 17:47
 */
public class DAOutil {
    public static User generateUserRecord(UserLoginRequest request){
        User record = new User();
        record.setOpenid(request.getOpenid());
        record.setAvatar(request.getAvatar());
        record.setGender(request.getGender());
        record.setMobile(request.getMobile());
        record.setNickName(request.getNickName());
        Long now = System.currentTimeMillis();
        record.setCreateTime(now);
        record.setLastLoginTime(now);
        return record;
    }

    public static String generateCardCid(){
        return "card_"+CryptUtil.md5(String.valueOf(System.currentTimeMillis())).substring(12);
    }

}
