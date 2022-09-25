package com.dragon.server.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: henry
 * @date: 2022/8/10 13:46
 */
@Configuration
@MapperScan(basePackages = {"com.dragon.server.db.mapper"})
@ComponentScan(basePackages = {"com.dragon.server"})
@EnableConfigurationProperties({DragonResourcesProperties.class})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ApplicationConfiguration implements WebMvcConfigurer {
}
