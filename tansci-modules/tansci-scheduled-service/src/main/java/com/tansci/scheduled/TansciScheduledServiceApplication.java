package com.tansci.scheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.tansci.*")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class TansciScheduledServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TansciScheduledServiceApplication.class, args);
    }

}
