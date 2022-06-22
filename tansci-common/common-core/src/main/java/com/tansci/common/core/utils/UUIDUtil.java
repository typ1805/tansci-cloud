package com.tansci.common.core.utils;

import java.util.UUID;

/**
 * @ClassName： UUIDUtil.java
 * @ClassPath： com.tansci.common.core.utils.UUIDUtil.java
 * @Description： UUID工具类
 * @Author： tanyp
 * @Date： 2022/2/11 10:01
 **/
public class UUIDUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String getUUID(Integer len) {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, len);
    }

}
