package com.springboot.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TradeServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradeServerApplication.class, args);
    }
}
