package com.wengzw.blood.gateway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wengzw
 * @date 2022/7/30 11:03
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.wengzw.blood.common.dao")
@ComponentScan(basePackages = {"com.wengzw"})
public class LoveBloodGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoveBloodGatewayApplication.class);
    }
}
