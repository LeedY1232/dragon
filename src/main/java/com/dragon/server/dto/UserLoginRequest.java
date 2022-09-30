package com.dragon.server.dto;

import lombok.Data;

/**
 * @author henry
 * @date 2022/9/29 17:25
 */
@Data
public class UserLoginRequest {

    private String openid;

    private String nickName;

    private String avatar;

    private Integer mobile;

    private Integer gender;
}
