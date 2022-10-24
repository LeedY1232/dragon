package com.dragon.server.db.field;

import java.util.HashMap;
import java.util.Map;

/**
 * @author henry
 * @date 2022/10/6 14:25
 */
public enum HouseStatus {
    NEW(101,"新创建"),
    WAITING_READY(102,"等待开始"),
    PLAY(103,"游戏中"),
    DESTROY(104,"已销毁")
    ;
    private Integer value;
    private String desc;

    private static Map<Integer,HouseStatus> valueMap = new HashMap<>();

    static {
        for(HouseStatus status:HouseStatus.values()){
            valueMap.put(status.value,status);
        }
    }

    HouseStatus(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static HouseStatus parse(Integer value){
        return value != null ? valueMap.get(value) : null;
    }
}
