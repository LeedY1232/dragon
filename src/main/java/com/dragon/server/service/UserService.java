package com.dragon.server.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dragon.server.dao.UserMapper;
import com.dragon.server.db.entity.User;
import com.dragon.server.dto.UserLoginRequest;
import com.dragon.server.exception.DataBaseException;
import com.dragon.server.util.DAOutil;

/**
 * @author henry
 * @date 2022/9/29 17:16
 */
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserMapper userMapper;


    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }


    public int insert(User record) {
        return userMapper.insert(record);
    }


    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    public void login(UserLoginRequest request) {
        try {
            User record = userMapper.selectByUniqueKey(request.getOpenid());
            if (record == null) {
                // 不存在则创建新角色
                userMapper.insert(DAOutil.generateUserRecord(request));
            } else {
                // 存在则更新最后登录时间，后可考虑转用消息队列完成，防止数据库流量过高
                record.setNickName(request.getNickName());
                record.setGender(record.getGender());
                record.setMobile(record.getMobile());
                record.setLastLoginTime(System.currentTimeMillis());
                userMapper.updateByPrimaryKeySelective(record);
            }
        } catch (Exception e) {
            logger.error("fail to get user login info.",e);
            // 交给全局异常处理，返回错误信息给客户端
            throw new DataBaseException("查询用户登录信息失败");
        }
    }
}
