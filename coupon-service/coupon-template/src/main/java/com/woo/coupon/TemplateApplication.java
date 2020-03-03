package com.woo.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description 模版微服务的启动入口
 * @Author WooLeo
 * @Date 2020/2/24 6:33 下午
 * @Version 1.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
@EnableJpaAuditing
public class TemplateApplication {
    public static void main(String[] args) {
        SpringApplication.run(TemplateApplication.class, args);
    }
}
