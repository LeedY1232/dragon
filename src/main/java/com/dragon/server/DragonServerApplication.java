package com.dragon.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.dragon.server.dao"})
public class DragonServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(DragonServerApplication.class, args);
  }
}
