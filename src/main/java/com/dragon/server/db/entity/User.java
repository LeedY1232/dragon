package com.dragon.server.db.entity;

import lombok.Data;

/**
 *  @author henry
 *  @date 2022/9/29 17:16
 */
@Data
public class User {
    private Integer id;

    private String openid;

    private String nickName;

    private String avatar;

    private Integer mobile;

    private Integer gender;

    private Long createTime;

    private Long lastLoginTime;
}