package com.dragon.server.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dragon.server.config.ApiResponse;
import com.dragon.server.controller.template.BaseApiController;
import com.dragon.server.db.entity.PunishCard;
import com.dragon.server.db.field.Gender;
import com.dragon.server.render.ViewConvertor;
import com.dragon.server.render.response.PunishCardResponseData;
import com.dragon.server.service.PunishCardService;
import com.dragon.server.util.CommonConvertorUtil;
import com.dragon.server.util.ParamCheckUtil;

/**
 * @author henry
 * @date 2022/9/29 17:20
 */
@RestController
public class PunishCardController extends BaseApiController {

    @Resource
    private PunishCardService punishCardService;

    @GetMapping(value="punishCard/offline/get")
    public ApiResponse getOfflineCards(Integer gender){
        Gender g = Gender.parse(gender);
        ParamCheckUtil.assertParameterNotNull(g,"错误的玩法类型");
        List<PunishCard> truths = punishCardService.getTruthCards(g);
        List<PunishCard> challenges = punishCardService.getChallengeCards(g);
        PunishCardResponseData data = PunishCardResponseData.Builder.aPunishCardResponseData()
                .setTruthPenalty(CommonConvertorUtil.convertToListViews(truths, ViewConvertor::convertToPunishCardView))
                .setChallengePenalty(CommonConvertorUtil.convertToListViews(challenges,ViewConvertor::convertToPunishCardView))
                .build();
        return ApiResponse.ok(data);
    }
}
