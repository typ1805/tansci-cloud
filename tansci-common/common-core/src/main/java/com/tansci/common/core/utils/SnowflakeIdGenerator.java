package com.tansci.common.core.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName： SnowflakeIdGenerator.java
 * @ClassPath： com.tansci.common.core.utils.SnowflakeIdGenerator.java
 * @Description： 雪花ID生成器
 * @Author： tanyp
 * @Date： 2022/2/11 9:59
 **/
public class SnowflakeIdGenerator {

    private static final SnowflakeIdUtiles snowflakeIdUtiles = new SnowflakeIdUtiles();

    /**
     * @methodName：createOrderNo
     * @description：生成订单号(按日期生成)
     * @author：tanyp
     * @dateTime：2022/2/11 9:59
     * @Params： []
     * @Return： java.lang.String
     * @editNote：
     */
    public static String createOrderNo() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + snowflakeIdUtiles.nextId();
    }

    /**
     * @methodName：nextId
     * @description：生成订单号（Long）
     * @author：tanyp
     * @dateTime：2022/2/11 9:59
     * @Params： []
     * @Return： java.lang.Long
     * @editNote：
     */
    public static Long nextId() {
        return snowflakeIdUtiles.nextId();
    }

    /**
     * @methodName：nextStringId
     * @description：生成订单号（字符串）
     * @author：tanyp
     * @dateTime：2022/2/11 9:59
     * @Params： []
     * @Return： java.lang.String
     * @editNote：
     */
    public static String nextStringId() {
        return String.valueOf(snowflakeIdUtiles.nextId());
    }

}
