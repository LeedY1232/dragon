package com.dragon.server.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author henry
 * @date 2022/10/6 14:40
 */
@Data
@Builder
public class HouseMemberModel {
    private String openid;
    private Long joinTime;
    private boolean ifReady;

    public HouseMemberModel(String openid, Long joinTime, boolean ifReady) {
        this.openid = openid;
        this.joinTime = joinTime;
        this.ifReady = ifReady;
    }
}
