package com.tansci.common.redisson.handler;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @ClassName： RedissonLockHandler.java
 * @ClassPath： com.tansci.common.redisson.handler.RedissonLockHandler.java
 * @Description： redisson分布式锁
 * @Author： tanyp
 * @Date： 2022/8/31 9:12
 **/
@Service
@Transactional
public class RedissonLockHandler {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * @MonthName： lock
     * @Description： 获取锁
     * @Author： tanyp
     * @Date： 2022/8/31 9:20
     * @Param： [key, waitTime, leaseTime, success, fail]
     * @return： T
     **/
    public <T> T lock(String key, int waitTime, int leaseTime, Supplier<T> success, Supplier<T> fail) throws Exception {
        /**
         * @Autowired
         * private RedissonLockHandler redissonLockHandler;
         *
         * return redissonLockHandler.lock("key",0,30000,()->{
         *    //获取锁成功执行逻辑
         *    return "获取锁成功";
         * },()->{
         *    //获取锁失败执行逻辑
         *    return "获取锁失败";
         * });
         */
        RLock lock = redissonClient.getLock(key);
        boolean flag = lock.tryLock(waitTime, leaseTime, TimeUnit.MILLISECONDS);
        try {
            if (flag) {
                return success.get();
            } else {
                return fail.get();
            }
        } finally {
            if (flag) {
                if (lock.isLocked()) {
                    lock.unlock();
                }
            }
        }
    }

}
