package com.dragon.server.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dragon.server.common.GlobalTimerService;
import com.dragon.server.dao.HouseMapper;
import com.dragon.server.dao.UserMapper;
import com.dragon.server.db.entity.House;
import com.dragon.server.db.entity.User;
import com.dragon.server.db.field.HouseMemberList;
import com.dragon.server.db.field.HouseStatus;
import com.dragon.server.exception.DataBaseException;
import com.dragon.server.model.HouseMemberModel;
import com.dragon.server.util.DAOutil;

/**
 * @author henry
 * @date 2022/10/6 14:23
 */
@Service
public class HouseService {

    private static final Logger logger = LoggerFactory.getLogger(HouseService.class);

    @Resource
    private HouseMapper houseMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private GlobalTimerService globalTimerService;

    @PostConstruct
    private void init(){
        globalTimerService.schedule(()->{
            try {
                List<House> deleteList = selectByDesStatus();
                for (House house : deleteList) {
                    deleteByPrimaryKey(house.getId());
                }
                logger.info("finish to delete house,size:{}",deleteList.size());
            }catch (Exception e){
                logger.error("fail to delete house.",e);
            }
        },3600,3600);
    }



    private static boolean allowJoin(House house) {
        HouseStatus status = HouseStatus.parse(house.getStatus());
        return status == HouseStatus.NEW || status == HouseStatus.WAITING_READY;
    }

    public int deleteByPrimaryKey(Integer id) {
        return houseMapper.deleteByPrimaryKey(id);
    }

    public int insert(House record) {
        return houseMapper.insert(record);
    }

    public int insertOrUpdate(House record) {
        return houseMapper.insertOrUpdate(record);
    }

    public int insertOrUpdateSelective(House record) {
        return houseMapper.insertOrUpdateSelective(record);
    }

    public House selectByPrimaryKey(Integer id) {
        return houseMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(House record) {
        return houseMapper.updateByPrimaryKeySelective(record);
    }

    public List<House> selectByDesStatus(){
        return houseMapper.selectByDesStatus();
    }

    public House selectByUniqueKey(Integer hid) {
        return houseMapper.selectByUniqueKey(hid);
    }

    public House createNewHouse(String openid) {
        User userRecord = userMapper.selectByUniqueKey(openid);
        if (userRecord == null) {
            throw new DataBaseException("非法用户，无法创建房间");
        }
        House record = new House();
        record.setHid(DAOutil.generateHouseNumber());
        record.setCreateUser(openid);
        record.setStatus(HouseStatus.NEW.getValue());
        long now = System.currentTimeMillis();
        record.setCreateTime(now);
        HouseMemberList memberList = new HouseMemberList();
        memberList.getMemberInfos().add(HouseMemberModel.builder()
                .openid(openid)
                .joinTime(now)
                .ifReady(false)
                .build());
        record.setMemberList(JSON.toJSONString(memberList));
        houseMapper.insert(record);
        return record;
    }

    public House joinHouse(Integer houseHid, String openid) {
        House record = houseMapper.selectByUniqueKey(houseHid);
        if (!allowJoin(record)) {
            throw new DataBaseException("游戏一开始或房间代码失效");
        }
        User player = userMapper.selectByUniqueKey(openid);
        if (player == null) {
            throw new DataBaseException("玩家不存在");
        }
        HouseMemberList memberList = JSON.parseObject(record.getMemberList(), HouseMemberList.class);
        Optional.ofNullable(memberList)
                .map(HouseMemberList::getMemberInfos)
                .ifPresent((models) -> {
                    models.add(HouseMemberModel.builder()
                            .openid(player.getOpenid())
                            .joinTime(System.currentTimeMillis())
                            .ifReady(false)
                            .build());
                    record.setMemberList(JSON.toJSONString(memberList));
                    houseMapper.updateByPrimaryKeySelective(record);
                });
        return record;
    }
}
