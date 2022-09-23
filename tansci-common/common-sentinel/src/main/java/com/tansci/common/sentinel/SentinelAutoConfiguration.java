package com.tansci.common.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import com.tansci.common.sentinel.handler.SentinelBlockHandler;
import com.tansci.common.sentinel.parser.HeaderRequestOriginParser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName： SentinelAutoConfiguration.java
 * @ClassPath： com.tansci.common.sentinel.SentinelAutoConfiguration.java
 * @Description： sentinel 配置
 * @Author： tanyp
 * @Date： 2022/9/23 15:38
 **/
@Configuration(proxyBeanMethods = false)
public class SentinelAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public BlockExceptionHandler blockExceptionHandler() {
        return new SentinelBlockHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public RequestOriginParser requestOriginParser() {
        return new HeaderRequestOriginParser();
    }

}
