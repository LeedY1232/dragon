package com.dragon.server.dao;

import java.util.List;

import com.dragon.server.db.entity.PunishCard;
import org.apache.ibatis.annotations.Mapper;

/**
 *  @author henry
 *  @date 2022/9/29 17:16
 */
@Mapper
public interface PunishCardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PunishCard record);

    PunishCard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PunishCard record);

    List<PunishCard> selectAllAvailableCards();
}