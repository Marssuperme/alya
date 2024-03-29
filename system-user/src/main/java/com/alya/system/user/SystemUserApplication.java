package com.alya.system.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Administrator
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SystemUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemUserApplication.class, args);
    }

}
