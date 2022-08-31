package com.tansci.common.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName： RedissonConfig.java
 * @ClassPath： com.tansci.common.redisson.config.RedissonConfig.java
 * @Description： Redisson配置类
 * @Author： tanyp
 * @Date： 2022/8/31 9:11
 **/
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.database}")
    private Integer database;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + host + ":" + port);
//                .setPassword(password)
//                .setDatabase(database);

        return Redisson.create(config);
    }

}
