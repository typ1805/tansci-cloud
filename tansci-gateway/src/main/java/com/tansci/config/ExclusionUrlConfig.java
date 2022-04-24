package com.tansci.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName： ExclusionUrlConfig.java
 * @ClassPath： com.tansci.config.ExclusionUrlConfig.java
 * @Description： 白名单配置
 * @Author： tanyp
 * @Date： 2022/2/11 10:38
 **/
@Data
@Component
@ConfigurationProperties(prefix = "exclusion")
public class ExclusionUrlConfig {

    private List<String> url;

}
