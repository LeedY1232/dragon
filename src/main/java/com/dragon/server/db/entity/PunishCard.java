package com.dragon.server.db.entity;

import lombok.Data;

/**
 *  @author henry
 *  @date 2022/9/29 17:16
 */
@Data
public class PunishCard {
    private Integer id;

    /**
    * 生成的字符类型的cid，用以区分唯一的card
    */
    private String cid;

    /**
    * 内容
    */
    private String content;

    /**
    * 劲爆程度
    */
    private Integer level;

    /**
    * 真心话？大冒险
    */
    private String category;

    /**
    * 聚会类别
    */
    private Integer partyKind;

    /**
    * 卡片状态
    */
    private Integer cardStatus;
}