package com.dragon.server.render.view;

import lombok.Data;

/**
 * @author henry
 * @date 2022/9/30 10:00
 */
@Data
public class PunishCardView {
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
}
