package com.dragon.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.dragon.common.base.config.BaseResourceProperties;

/**
 * @author Li Dongyang
 * @date 2022/9/24 22:00
 */
@ConfigurationProperties(BaseResourceProperties.PREFIX)
public class DragonResourcesProperties extends BaseResourceProperties {
    public String getDbUrl() {
        return dbMaster.getUrl();
    }

    public String getDbUsername() {
        return dbMaster.getUsername();
    }

    public String getDbPassword() {
        return dbMaster.getPassword();
    }
}
