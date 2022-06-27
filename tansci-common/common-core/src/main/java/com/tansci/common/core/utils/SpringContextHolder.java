package com.tansci.common.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @ClassName： SpringContextHolder.java
 * @ClassPath： com.tansci.common.core.utils.SpringContextHolder.java
 * @Description： spring 上下文
 * @Author： tanyp
 * @Date： 2022/6/27 11:44
 **/
@Slf4j
@Component
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    /**
     * @MonthName： getApplicationContext
     * @Description： 取得存储在静态变量中的 ApplicationContext
     * @Author： tanyp
     * @Date： 2022/6/27 11:46
     * @Param： []
     * @return： org.springframework.context.ApplicationContext
     **/
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @MonthName： getBean
     * @Description： 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型
     * @Author： tanyp
     * @Date： 2022/6/27 11:46
     * @Param： [name]
     * @return： T
     **/
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    /**
     * @MonthName： getBean
     * @Description： 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型
     * @Author： tanyp
     * @Date： 2022/6/27 11:47
     * @Param： [requiredType]
     * @return： T
     **/
    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    /**
     * @MonthName： clearHolder
     * @Description： 清除SpringContextHolder中的ApplicationContext为Null
     * @Author： tanyp
     * @Date： 2022/6/27 11:48
     * @Param： []
     * @return： void
     **/
    public static void clearHolder() {
        if (log.isDebugEnabled()) {
            log.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
        }
        applicationContext = null;
    }

    /**
     * @MonthName： destroy
     * @Description： 实现DisposableBean接口, 在Context关闭时清理静态变量
     * @Author： tanyp
     * @Date： 2022/6/27 11:49
     * @Param： []
     * @return： void
     **/
    @Override
    public void destroy() throws Exception {
        SpringContextHolder.clearHolder();
    }

    /**
     * @MonthName： setApplicationContext
     * @Description： 实现ApplicationContextAware接口, 注入Context到静态变量中
     * @Author： tanyp
     * @Date： 2022/6/27 11:48
     * @Param： [applicationContext]
     * @return： void
     **/
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

}
