package com.dragon.server.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dragon.common.base.db.entity.PunishCard;
import com.dragon.common.base.db.field.Gender;
import com.dragon.common.base.db.field.PunishCardCategory;
import com.dragon.common.base.db.field.PunishCardLevel;
import com.dragon.server.common.GlobalTimerService;
import com.dragon.server.manager.dto.PunishCardManager;
import com.dragon.server.manager.model.CardIndexKey;

/**
 * @author Li Dongyang
 * @date 2022/9/24 22:42
 */
@Service
public class PunishCardService {

    private static final Logger logger = LoggerFactory.getLogger(PunishCardService.class);
    @Resource
    private PunishCardManager punishCardManager;

    @Resource
    private GlobalTimerService globalTimerService;

    private static Map<CardIndexKey,List<PunishCard>> CARD_MAP_BY_INDEX = new HashMap<>();

    @PostConstruct
    private void init(){
        load();
        try{
            globalTimerService.schedule(this::load,30,60);
        }catch (Exception e){
            logger.error("fail to load punish cards from db.",e);
        }
    }


    // 从内存中加载惩罚卡片，加快获取速度
    public void load(){
        logger.info("start to load punish cards from db.");
        List<PunishCard> cards = punishCardManager.getAllCards();
        Map<CardIndexKey,List<PunishCard>> cardIndexKeyListMap = new HashMap<>();
        cards.forEach(card->{

            CardIndexKey indexKey = new CardIndexKey(
                    null,//PunishCardLevel.parse(card.getLevel()),
                    PunishCardCategory.parse(card.getCategory()),
                    Gender.parse(card.getGender()));
            cardIndexKeyListMap.computeIfAbsent(indexKey,k->new ArrayList<>());
            cardIndexKeyListMap.get(indexKey).add(card);
        });
        cardIndexKeyListMap.forEach((key,cardList)->{
            logger.info("load key:{} from db, {} items",key,cardList.size());
        });
        CARD_MAP_BY_INDEX = cardIndexKeyListMap;
        logger.info("finish to load cards.");
    }

    public List<PunishCard> getCardsFromCache(PunishCardLevel level, PunishCardCategory category, Gender gender){
        CardIndexKey indexKey = new CardIndexKey(level,category,gender);
        return Optional.ofNullable(CARD_MAP_BY_INDEX)
                .map((map)->map.get(indexKey))
                .orElseGet(ArrayList::new);


    }
}
