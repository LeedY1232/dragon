package com.dragon.server.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dragon.common.base.db.entity.PunishCard;
import com.dragon.common.base.db.field.Gender;
import com.dragon.common.base.db.field.PunishCardCategory;
import com.dragon.common.base.transport.BaseApiResponse;
import com.dragon.common.base.transport.ResponseCode;
import com.dragon.common.base.util.CommonConvertorUtil;
import com.dragon.common.base.util.ParamCheckUtil;
import com.dragon.server.manager.PunishCardService;
import com.dragon.server.render.ViewConvertor;
import com.dragon.server.web.response.PunishCardResponseData;

/**
 * @author Li Dongyang
 * @date 2022/9/25 18:30
 */
@RestController
public class PunishCardController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(PunishCardController.class);
    @Resource
    private PunishCardService punishCardService;

    @GetMapping(value="punishCard/get")
    public BaseApiResponse getCard(String gender){
//        ParamCheckUtil.assertParameterNotBlank(level,"level can't be blank");
//        ParamCheckUtil.assertParameterNotBlank(category,"category can't be blank");
        ParamCheckUtil.assertParameterNotBlank(gender,"gender can't be blank");
        Gender ggender = Gender.parse(gender);
        List<PunishCard> truthCards = punishCardService.getCardsFromCache(null, PunishCardCategory.TRUTH, ggender);
        List<PunishCard> challengeCards = punishCardService.getCardsFromCache(null,PunishCardCategory.CHALLENGE,ggender);
        PunishCardResponseData data = PunishCardResponseData.Builder.aPunishCardResponseData()
                .setTruthPenalty(CommonConvertorUtil.convertToListViews(truthCards, ViewConvertor::convertToPunishCardView))
                .setChallengePenalty(CommonConvertorUtil.convertToListViews(challengeCards,ViewConvertor::convertToPunishCardView))
                .build();
        return BaseApiResponse.generateResponseWithData(ResponseCode.SUCCESS, data);
    }
}
