package com.dragon.server.db.field;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Li Dongyang
 * @date 2022/9/25 17:58
 */
public enum PunishCardCategory {
    TRUTH(1, "truth"),
    CHALLENGE(2, "challenge"),
    MAGIC_CARD(3, "magic_card");
    private static final Map<String, PunishCardCategory> codeMap = new HashMap<>();

    static {
        for (PunishCardCategory cardCategory : PunishCardCategory.values()) {
            codeMap.put(cardCategory.desc, cardCategory);
        }
    }

    private Integer code;
    private String desc;

    PunishCardCategory(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PunishCardCategory parse(String code) {
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

    public String value() {
        return this.desc;
    }
}
