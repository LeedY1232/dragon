package com.dragon.server.db.field;

import java.util.HashMap;
import java.util.Map;

/**
 * @author henry
 * @date 2022/9/26 22:28
 */
public enum PunishCardStatus {
    NORMAL(11, "正常"),
    DELETE(12, "已删除"),
    ;
    private static final Map<Integer, PunishCardStatus> codeMap = new HashMap<>();

    static {
        for (PunishCardStatus status : PunishCardStatus.values()) {
            codeMap.put(status.code, status);
        }
    }

    private Integer code;
    private String desc;

    PunishCardStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PunishCardStatus parse(Integer code) {
        return code != null ? codeMap.get(code) : null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
