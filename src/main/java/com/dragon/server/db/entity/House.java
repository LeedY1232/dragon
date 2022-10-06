package com.dragon.server.db.entity;

import lombok.Data;

/**
 *  @author henry
 *  @date 2022/10/6 14:23
 */
@Data
public class House {
    private Integer id;

    private Integer hid;

    private String createUser;

    private Integer status;

    private Long createTime;

    private String memberList;
}