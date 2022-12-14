package com.dragon.server.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dragon.server.common.GlobalTimerService;
import com.dragon.server.dao.PunishCardMapper;
import com.dragon.server.db.entity.PunishCard;
import com.dragon.server.db.field.Gender;
import com.dragon.server.db.field.PunishCardCategory;
import com.dragon.server.exception.DataBaseException;
import com.dragon.server.util.CommonUtil;

/**
 * @author henry
 * @date 2022/9/29 17:16
 */
@Service
public class PunishCardService {
    private static final Logger logger = LoggerFactory.getLogger(PunishCardService.class);

    private static Map<PunishCardCategory, List<PunishCard>> CARD_MAP;

    @Resource
    private PunishCardMapper punishCardMapper;

    @Resource
    private GlobalTimerService globalTimerService;

    @PostConstruct
    private void init() {
        load();
        try {
            globalTimerService.schedule(this::load, 60, 500);
        } catch (Exception e) {
            logger.error("fail to load punish card into cache.", e);
        }
    }

    private void load() {
        logger.info("start to load cards.");
        Map<PunishCardCategory, List<PunishCard>> cardMap = getAllAvailableCards();
        // 统计
        long total = cardMap.values().stream().flatMap(Collection::stream).count();
        if (total > 0) { // 仅当获取到数据才替换，否则不替换
            CARD_MAP = cardMap;

            logger.info("finish to load card infos.size:{}", total);
        } else {
            logger.warn("something may be wrong in database.");
        }
    }

    private Map<PunishCardCategory, List<PunishCard>> getAllAvailableCards() {
        List<PunishCard> cards = punishCardMapper.selectAllAvailableCards();
        Map<PunishCardCategory, List<PunishCard>> res = new HashMap<>();
        cards.stream().filter(Objects::nonNull)
                .forEach(card -> {
                    res.computeIfAbsent(PunishCardCategory.parse(card.getCategory()), k -> new ArrayList<>());
                    res.get(PunishCardCategory.parse(card.getCategory())).add(card);
                });
        return res;
    }


    public int deleteByPrimaryKey(Integer id) {
        return punishCardMapper.deleteByPrimaryKey(id);
    }


    public int insert(PunishCard record) {
        return punishCardMapper.insert(record);
    }


    public PunishCard selectByPrimaryKey(Integer id) {
        return punishCardMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(PunishCard record) {
        return punishCardMapper.updateByPrimaryKeySelective(record);
    }

    public List<PunishCard> getTruthCards(Gender gender) {
        List<PunishCard> cards = Optional.of(CARD_MAP)
                .map(map -> map.get(PunishCardCategory.TRUTH))
                .filter(CommonUtil::isNotEmpty)
                .orElseThrow(() -> new DataBaseException("获取真心话列表失败"));
        return cards.stream().filter(card -> gender == Gender.parse(card.getPartyKind()))
                .collect(Collectors.toList());
    }

    public List<PunishCard> getChallengeCards(Gender gender) {
        List<PunishCard> cards = Optional.of(CARD_MAP)
                .map(map -> map.get(PunishCardCategory.CHALLENGE))
                .filter(CommonUtil::isNotEmpty)
                .orElseThrow(() -> new DataBaseException("获取大冒险列表失败"));
        return cards.stream().filter(card -> gender == Gender.parse(card.getPartyKind()))
                .collect(Collectors.toList());
    }
}
