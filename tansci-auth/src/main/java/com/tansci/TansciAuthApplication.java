package com.tansci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TansciAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(TansciAuthApplication.class, args);
    }

}
