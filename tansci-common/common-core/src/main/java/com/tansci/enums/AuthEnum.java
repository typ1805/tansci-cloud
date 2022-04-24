package com.tansci.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @ClassName： AuthEnum.java
 * @ClassPath： com.tansci.enums.AuthEnum.java
 * @Description： 安全认证相关
 * @Author： tanyp
 * @Date： 2022/2/11 9:50
 **/
@Getter
@AllArgsConstructor
public enum AuthEnum {

    AUTH_NO_TOKEN(401, "Token已过期或有误"),

    AUTH_NO_ACCESS(403, "无访问权限"),

    AUTH_NONEXISTENT(404, "请求路径不存在"),

    ;

    private Integer key;

    private String value;

    /**
     * @MonthName： getValue
     * @Description： 根据key获取value
     * @Author： tanyp
     * @Date： 2022/2/11 9:52
     * @Param： [key]
     * @return： java.lang.String
     **/
    public static String getValue(Integer key) {
        for (AuthEnum value : AuthEnum.values()) {
            if (Objects.equals(key, value.getKey())) {
                return value.getValue();
            }
        }
        return null;
    }

}
