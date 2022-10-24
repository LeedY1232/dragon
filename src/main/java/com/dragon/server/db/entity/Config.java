package com.dragon.server.db.entity;

import lombok.Data;

/**
 *  @author henry
 *  @date 2022/10/24 14:59
 */
@Data
public class Config {
    private Integer id;

    private String configName;

    private String configValue;

    private Long updateTime;
}