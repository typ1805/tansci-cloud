package com.tansci.constants;

/**
 * @ClassName： Constants.java
 * @ClassPath： com.tansci.constants.Constants.java
 * @Description： 常量
 * @Author： tanyp
 * @Date： 2022/2/11 9:48
 **/
public class Constants {

    /**
     * 成功
     */
    public static final Integer SUCCESS = 200;

    public static final String SUCCESS_MESSAGE = "操作成功！";

    /**
     * 失败
     */
    public static final Integer ERROR = 500;

    public static final String ERROR_MESSAGE = "服务器异常，请稍后再试！";

    public static final String LOGIN_MESSAGE = "登录失败，用户名或密码有误！";

    public static final String LOGOUT_MESSAGE = "退出成功！";

    public static final String USER_INFO_MESSAGE = "用户信息不存在！";

    /**
     * 接口类型
     */
    public final static String SELECT = "SELECT";

    public final static String INSERT = "INSERT";

    public final static String UPDATE = "UPDATE";

    public final static String DELETE = "DELETE";

    /**
     * 任务调度默认启动10个线程
     */
    public final static Integer DEFAULT_THREAD_POOL = 10;

}
