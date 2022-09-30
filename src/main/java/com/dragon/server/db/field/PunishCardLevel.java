package com.dragon.server.db.field;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Li Dongyang
 * @date 2022/9/25 17:58
 */
public enum PunishCardLevel {
    EASY(1),
    MIDDLE(2),
    CRAZY(3);
    private static final Map<Integer, PunishCardLevel> codeMap = new HashMap<>();

    static {
        for (PunishCardLevel level : PunishCardLevel.values()) {
            codeMap.put(level.code, level);
        }
    }

    private Integer code;

    PunishCardLevel(Integer code) {
        this.code = code;
    }

    public static PunishCardLevel parse(Integer code) {
        return code != null ? codeMap.get(code) : null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer value() {
        return this.code;
    }
}
