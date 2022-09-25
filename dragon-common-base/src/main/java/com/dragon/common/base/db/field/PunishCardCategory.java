package com.dragon.common.base.db.field;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Li Dongyang
 * @date 2022/9/25 17:58
 */
public enum PunishCardCategory {
    TRUTH("truth","真心话"),
    CHALLENGE("challenge","大冒险"),
    MAGIC_CARD("magic_card","魔法卡片")
    ;
    private String code;
    private String desc;

    private static final Map<String,PunishCardCategory> codeMap = new HashMap<>();

    static {
        for(PunishCardCategory cardCategory:PunishCardCategory.values()){
            codeMap.put(cardCategory.code,cardCategory);
        }
    }

    PunishCardCategory(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String value(){
        return this.code;
    }

    public static PunishCardCategory parse(String code){
        return StringUtils.isNotBlank(code) ? codeMap.get(code) : null;
    }
}
