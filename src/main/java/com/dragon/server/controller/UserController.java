package com.dragon.server.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.dragon.server.config.ApiResponse;
import com.dragon.server.controller.template.BaseApiController;
import com.dragon.server.dto.UserLoginRequest;
import com.dragon.server.service.UserService;
import com.dragon.server.util.ParamCheckUtil;

/**
 * @author henry
 * @date 2022/9/29 17:23
 */
@RestController
public class UserController extends BaseApiController {
    @Resource
    private UserService userService;

    @PostMapping(value = "user/login")
    public ApiResponse login(@RequestBody String body) {
        UserLoginRequest request = JSON.parseObject(body, UserLoginRequest.class);
        ParamCheckUtil.assertParameterNotNull(request, "错误的请求格式");
        ParamCheckUtil.assertParameterNotBlank(request.getOpenid(), "openid不能为空");
        userService.login(request);
        return ApiResponse.success("登录成功");
    }
}
