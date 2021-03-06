package com.tansci.common.web.annotation;

import java.lang.annotation.*;

/**
 * @ClassName： Log.java
 * @ClassPath： com.tansci.common.web.annotation.Log.java
 * @Description： 自定义操作日志注解
 * @Author： tanyp
 * @Date： 2022/2/15 8:50
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 操作模块
     *
     * @return
     */
    String modul() default "";

    /**
     * 操作类型
     *
     * @return
     */
    String type() default "";

    /**
     * 操作说明
     *
     * @return
     */
    String desc() default "";

}
