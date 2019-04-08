package com.springboot.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.springboot.api.facade"})
public class AdminServer {
    public static void main(String[] args) {
        SpringApplication.run(AdminServer.class, args);
    }
}
