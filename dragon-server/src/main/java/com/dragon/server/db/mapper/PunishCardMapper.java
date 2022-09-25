package com.dragon.server.db.mapper;

import java.util.List;

import com.dragon.common.base.db.entity.PunishCard;

/**
   *@author Li Dongyang
   *@date 2022/9/24 22:38
*/
public interface PunishCardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PunishCard record);

    PunishCard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PunishCard record);

    List<PunishCard> selectAllCards();
}