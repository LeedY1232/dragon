package com.dragon.server.db.field;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Li Dongyang
 * @date 2022/9/25 17:59
 */
public enum Gender {
    MALE(1),
    FEMALE(2),
    FIX(3);
    private static final Map<Integer, Gender> codeMap = new HashMap<>();

    static {
        for (Gender gender : Gender.values()) {
            codeMap.put(gender.code, gender);
        }
    }

    private Integer code;

    Gender(Integer code) {
        this.code = code;
    }

    public static Gender parse(Integer code) {
        return code != null ? codeMap.get(code) : null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


}
