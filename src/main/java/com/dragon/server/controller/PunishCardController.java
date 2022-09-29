package com.dragon.server.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dragon.server.config.ApiResponse;
import com.dragon.server.controller.template.BaseApiController;
import com.dragon.server.db.entity.PunishCard;
import com.dragon.server.service.PunishCardService;

/**
 * @author henry
 * @date 2022/9/29 17:20
 */
@RestController
public class PunishCardController extends BaseApiController {

    @Resource
    private PunishCardService punishCardService;

    @GetMapping(value="punishCard/offline/get")
    public ApiResponse getOfflineCards(){
        List<PunishCard> truths = punishCardService.getTruthCards();
        List<PunishCard> challenges = punishCardService.getChallengeCards();
    }
}
