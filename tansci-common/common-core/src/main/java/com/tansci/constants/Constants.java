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
     * 资源域名前缀
     */
    public final static String RESOURCE_PREFIX = "mps-cloud";

    /**
     * 删除状态
     */
    public static final Integer IS_DELETE = 0;

    /**
     * 正常状态
     */
    public static final Integer NOT_DELETE = 1;

    /**
     * 禁用状态
     */
    public static final Integer DISABLE = 2;

    /**
     * 上架状态
     */
    public static final Integer ON_THE_SHELF = 1;

    /**
     * 下架状态
     */
    public static final Integer OFF_THE_SHELF = 0;

    /**
     * 默认
     */
    public static final Integer IS_DEFAULT = 1;

    /**
     * 非默认
     */
    public static final Integer NOT_DEFAULT = 0;

    /**
     * 女
     */
    public static final Integer SEX_WOMAN = 1;

    /**
     * 男
     */
    public static final Integer SEX_MAN = 0;

    /**
     * 已锁定无法登陆
     */
    public static final Integer IS_LOCKED = 1;

    /**
     * 未锁定
     */
    public static final Integer NOT_LOCKED = 0;


}
