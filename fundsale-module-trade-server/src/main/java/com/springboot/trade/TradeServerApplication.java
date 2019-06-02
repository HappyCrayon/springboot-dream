package com.springboot.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient
public class TradeServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradeServerApplication.class, args);
    }
}
