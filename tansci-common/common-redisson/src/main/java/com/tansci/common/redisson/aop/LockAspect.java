package com.tansci.common.redisson.aop;

import com.tansci.common.redisson.annotation.Lock;
import com.tansci.common.redisson.exception.LockException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName： LockAspect.java
 * @ClassPath： com.tansci.common.redisson.aop.LockAspect.java
 * @Description： 分布式锁切面
 * @Author： tanyp
 * @Date： 2022/8/31 9:07
 **/
@Aspect
@Component
public class LockAspect {

    @Autowired
    private RedissonClient redissonClient;

    @Around("@annotation(com.tansci.common.redisson.annotation.Lock)")
    public Object interceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Lock lock = method.getAnnotation(Lock.class);

        String lockKey = getLockKeyName(lock, joinPoint);
        RLock rLock = redissonClient.getLock(lockKey);
        boolean flag = getLock(rLock, lock);
        try {
            if (flag) {
                return joinPoint.proceed();
            } else {
                throw new LockException("网络繁忙,请稍后再试");
            }
        } catch (Exception e) {
            throw new LockException("服务器繁忙,请稍后再试");
        } finally {
            if (flag) {
                if (rLock.isLocked()) {
                    rLock.unlock();
                }
            }
        }
    }

    private String getLockKeyName(Lock lock, ProceedingJoinPoint joinPoint) {
        String lockKeyPrefix = lock.lockKey();
        String businessCode = lock.businessCode();
        if (Objects.nonNull(lockKeyPrefix) && Objects.nonNull(businessCode)) {
            return lockKeyPrefix + ":" + businessCode;
        } else if (Objects.nonNull(lockKeyPrefix)) {
            return lockKeyPrefix;
        } else if (Objects.nonNull(businessCode)) {
            return businessCode;
        } else {
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            return className + "." + methodName;
        }
    }

    private boolean getLock(RLock rLock, Lock lock) throws InterruptedException {
        int waitTime = lock.waitTime();
        int leaseTime = lock.leaseTime();
        boolean flag = false;
        if (waitTime > 0 && leaseTime > 0) {
            flag = rLock.tryLock(waitTime, leaseTime, TimeUnit.MICROSECONDS);
        } else if (leaseTime > 0) {
            flag = rLock.tryLock(0, leaseTime, TimeUnit.MILLISECONDS);
        } else if (waitTime > 0) {
            flag = rLock.tryLock(waitTime, TimeUnit.MILLISECONDS);
        } else {
            flag = rLock.tryLock();
        }
        return flag;
    }

}
