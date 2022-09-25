package com.dragon.server.manager.dto;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dragon.common.base.db.entity.PunishCard;
import com.dragon.server.db.mapper.PunishCardMapper;

/**
 * @author Li Dongyang
 * @date 2022/9/24 22:38
 */
@Service
public class PunishCardManager {

    @Resource
    private PunishCardMapper punishCardMapper;


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

    public List<PunishCard> getAllCards(){
        return punishCardMapper.selectAllCards();
    }



}
