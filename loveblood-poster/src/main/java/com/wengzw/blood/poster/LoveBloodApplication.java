package com.wengzw.blood.poster;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wengzw
 * @date 2022/7/26 0:15
 */
@SpringBootApplication (
        exclude = {SecurityAutoConfiguration.class}
)
@EnableRabbit // 开启rabbitMQ
@EnableFeignClients // 开启服务调用
@EnableDiscoveryClient // 开启服务发现
@MapperScan({"com.wengzw.blood.common.dao","com.wengzw.blood.poster.dao"})  // 必须指定扫描的包
@ComponentScan(basePackages = {"com.wengzw"})
public class LoveBloodApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoveBloodApplication.class,args);
        System.out.println("================爱献血启动成功===================");
    }
}
