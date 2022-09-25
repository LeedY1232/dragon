package com.dragon.server.web.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dragon.common.base.db.entity.UserInfo;
import com.dragon.common.base.transport.ResponseCode;
import com.dragon.common.base.transport.BaseApiResponse;
import com.dragon.server.manager.UserInfoService;

/**
 * @author Li Dongyang
 * @date 2022/9/24 22:42
 */
@RestController
public class UserInfoController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Resource
    private UserInfoService userInfoService;

    @GetMapping(value = "user/info/get")
    public BaseApiResponse getUserInfo(String openid,String avatar, String username){
        UserInfo user = userInfoService.getByOpenId(openid);
        if(user == null){
            try{
                user = userInfoService.createUserInfo(openid,avatar,username);
            }catch (Exception e){
                logger.error("fail to get userinfo and create user info.",e);
                return BaseApiResponse.generateResponseByCode(ResponseCode.SYS_BUSY);
            }
        }
        return BaseApiResponse.generateResponseWithData(ResponseCode.SUCCESS,user);
    }
}
