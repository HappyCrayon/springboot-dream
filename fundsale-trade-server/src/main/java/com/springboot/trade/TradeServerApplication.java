package com.springboot.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableEurekaClient
@EnableAsync
public class TradeServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradeServerApplication.class, args);
    }
}
