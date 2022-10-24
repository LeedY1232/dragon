package com.dragon.server.dao;

import java.util.List;

import com.dragon.server.db.entity.Config;
import org.apache.ibatis.annotations.Mapper;

/**
 *  @author henry
 *  @date 2022/10/24 14:59
 */
@Mapper
public interface ConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Config record);

    int insertOrUpdate(Config record);

    int insertOrUpdateSelective(Config record);

    Config selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Config record);

    List<Config> selectAllConfigs();
}