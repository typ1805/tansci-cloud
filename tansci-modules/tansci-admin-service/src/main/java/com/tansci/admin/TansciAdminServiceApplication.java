package com.tansci.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.tansci.*")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class TansciAdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TansciAdminServiceApplication.class, args);
    }

}
