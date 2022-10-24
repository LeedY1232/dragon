package com.dragon.server.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSON;
import com.dragon.server.config.ApiResponse;
import com.dragon.server.db.entity.House;
import com.dragon.server.service.HouseService;
import com.dragon.server.util.ParamCheckUtil;

/**
 * @author henry
 * @date 2022/10/6 14:09
 */
public class HouseController {
    private static final Logger logger = LoggerFactory.getLogger(HouseController.class);

    @Resource
    private HouseService houseService;

    @PostMapping(value = "/house/create")
    public ApiResponse getHouseNumber(@RequestBody String body) {
        BaseHouseRequest request = JSON.parseObject(body, BaseHouseRequest.class);
        ParamCheckUtil.assertParameterNotNull(request, "请求格式错误");
        House newHouse = houseService.createNewHouse(request.openid);
        return ApiResponse.ok(newHouse);
    }

    @PostMapping(value = "house/join")
    public ApiResponse joinHouse(@RequestBody String body) {
        JoinHouseRequest request = JSON.parseObject(body, JoinHouseRequest.class);
        ParamCheckUtil.assertParameterNotNull(request, "请求格式错误");
        try {
            House record = houseService.joinHouse(request.houseHid, request.openid);
            if (record != null) {
                return ApiResponse.ok();
            }
        } catch (Exception e) {
            logger.error("fail to join house,body:{}", body, e);
        }
        return ApiResponse.error("系统错误");
    }

//    @PostMapping(value = "house/ready")
//    public ApiResponse readyRole(@RequestBody String body) {
//        ReadyRoleRequest request = JSON.parseObject(body, ReadyRoleRequest.class);
//        ParamCheckUtil.assertParameterNotNull(request,"请求格式错误");
//        try{
//
//        }
//    }

    public static class BaseHouseRequest {
        String openid;
    }

    public static class JoinHouseRequest extends BaseHouseRequest {
        Integer houseHid;
    }

    public static class ReadyRoleRequest extends BaseHouseRequest {
        Integer houseHid;
    }
}
