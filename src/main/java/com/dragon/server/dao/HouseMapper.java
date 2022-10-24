package com.dragon.server.dao;

import java.util.List;

import com.dragon.server.db.entity.House;
import org.apache.ibatis.annotations.Mapper;

/**
 *  @author henry
 *  @date 2022/10/6 14:23
 */
@Mapper
public interface HouseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(House record);

    int insertOrUpdate(House record);

    int insertOrUpdateSelective(House record);

    House selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(House record);

    House selectByUniqueKey(Integer hid);

    List<House> selectByDesStatus();
}