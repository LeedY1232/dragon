package com.dragon.server.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.dragon.server.common.GlobalTimerService;
import com.dragon.server.db.entity.Config;
import com.dragon.server.dao.ConfigMapper;
/**
 *  @author henry
 *  @date 2022/10/24 14:59
 */
@Service
public class ConfigService{

    @Resource
    private ConfigMapper configMapper;

    @Resource
    private GlobalTimerService globalTimerService;

    private static Map<String,String> configMap = new HashMap<>();

    @PostConstruct
    private void init(){
        loadConfigs();
        globalTimerService.schedule(this::loadConfigs,20,50);
    }

    public void loadConfigs(){
        List<Config> configs = configMapper.selectAllConfigs();
        configMap = configs.stream().collect(Collectors.toMap(Config::getConfigName,Config::getConfigValue,(ov,nv)->nv));
    }
    
    public int deleteByPrimaryKey(Integer id) {
        return configMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Config record) {
        return configMapper.insert(record);
    }

    
    public int insertOrUpdate(Config record) {
        return configMapper.insertOrUpdate(record);
    }

    
    public int insertOrUpdateSelective(Config record) {
        return configMapper.insertOrUpdateSelective(record);
    }

    
    public Config selectByPrimaryKey(Integer id) {
        return configMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Config record) {
        return configMapper.updateByPrimaryKeySelective(record);
    }

    public String getConfigByName(String configName){
        return configName != null ? configMap.get(configName) : null;
    }

}
