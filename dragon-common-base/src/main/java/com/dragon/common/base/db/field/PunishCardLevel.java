package com.dragon.common.base.db.field;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Li Dongyang
 * @date 2022/9/25 17:58
 */
public enum PunishCardLevel {
    EASY("1"),
    MIDDLE("2"),
    CRAZY("3")
    ;
    private String code;

    private static final Map<String,PunishCardLevel> codeMap = new HashMap<>();

    static {
        for(PunishCardLevel level:PunishCardLevel.values()){
            codeMap.put(level.code,level);
        }
    }

    PunishCardLevel(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String value(){
        return this.code;
    }

    public static PunishCardLevel parse(String code){
        return StringUtils.isNotBlank(code) ? codeMap.get(code) : null;
    }
}
