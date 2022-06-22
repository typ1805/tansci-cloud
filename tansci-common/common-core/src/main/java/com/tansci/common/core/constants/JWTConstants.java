package com.tansci.common.core.constants;

/**
 * @ClassName： JWTConstants.java
 * @ClassPath： com.tansci.common.core.constants.JWTConstants.java
 * @Description： JWT常量
 * @Author： tanyp
 * @Date： 2022/2/11 9:48
 **/
public class JWTConstants {

    public static final byte[] SECRET = "52d907a4b404af790cf2cf488acc4836".getBytes();

    public static final String TOKEN_HEADER = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 角色开头
     */
    public static final String ROLE_PREFIX = "ROLE_";

    /**
     * 商户角色
     */
    public static final String ROLE_MERCHANTS = "ROLE_MERCHANTS";

    /**
     * 过期时间2小时
     */
    public static final long EXPIRE_TIME = 1000 * 60 * 60 * 2;

    /**
     * Token已过期
     */
    public static final String TOKEN_EXPIRE = "Token已过期";

    /**
     * Token无效
     */
    public static final String TOKEN_INVALID = "Token无效";

}
