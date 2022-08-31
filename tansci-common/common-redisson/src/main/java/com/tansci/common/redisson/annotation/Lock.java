package com.tansci.common.redisson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName： Lock.java
 * @ClassPath： com.tansci.common.redisson.annotation.Lock.java
 * @Description： 加锁注解
 *
 * <p>
 * 示例：@Lock(lockKey = "key", businessCode = "ABEODS", waitTime = 0, leaseTime = 30000)
 * </p>
 * @Author： tanyp
 * @Date： 2022/8/30 15:22
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Lock {

    /**
     * 锁的key
     */
    String lockKey() default "";

    /**
     * 业务code
     */
    String businessCode() default "";

    /**
     * 获取锁的等待时间(毫秒)
     */
    int waitTime() default 0;

    /**
     * 释放的时间(毫秒)
     */
    int leaseTime() default 0;

}
