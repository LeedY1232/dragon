package com.dragon.common.base.db.field;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Li Dongyang
 * @date 2022/9/25 17:59
 */
public enum Gender {
    MALE("1"),
    FEMALE("2"),
    FIX("3")
    ;
    private String code;

    private static final Map<String,Gender> codeMap = new HashMap<>();

    static {
        for(Gender gender:Gender.values()){
            codeMap.put(gender.code,gender);
        }
    }

    Gender(String code) {
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

    public static Gender parse(String code){
        return StringUtils.isNotBlank(code) ? codeMap.get(code) : null;
    }


}
