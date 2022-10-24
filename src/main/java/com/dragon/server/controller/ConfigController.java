package com.dragon.server.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.dragon.server.config.ApiResponse;
import com.dragon.server.controller.template.BaseApiController;
import com.dragon.server.service.ConfigService;

/**
 * @author henry
 * @date 2022/10/24 15:06
 */
@RestController
public class ConfigController extends BaseApiController {

    @Resource
    private ConfigService configService;

    @PostMapping(value = "config/key")
    public ApiResponse getConfig(@RequestBody String body) {
        ConfigPostBody key = JSON.parseObject(body, ConfigPostBody.class);
        String message = configService.getConfigByName(key.key);
        return ApiResponse.ok(message);
    }

    private static class ConfigPostBody {
        public String key;
    }
}
