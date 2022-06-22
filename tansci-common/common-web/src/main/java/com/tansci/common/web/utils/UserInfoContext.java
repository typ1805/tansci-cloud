package com.tansci.common.web.utils;

import com.tansci.common.core.base.UserInfo;

/**
 * @ClassName： UserInfoContext.java
 * @ClassPath： com.tansci.common.web.utils.UserInfoContext.java
 * @Description： 用户登录信息
 * @Author： tanyp
 * @Date： 2022/2/22 15:08
 **/
public class UserInfoContext {

    private static final ThreadLocal<UserInfo> context = new ThreadLocal<>();

    public static UserInfo getUser() {
        return context.get();
    }

    public static void setUser(UserInfo userInfo) {
        context.set(userInfo);
    }

}
