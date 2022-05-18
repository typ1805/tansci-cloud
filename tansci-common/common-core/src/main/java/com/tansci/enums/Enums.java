package com.tansci.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @ClassName： Enums.java
 * @ClassPath： com.tansci.enums.Enums.java
 * @Description： 公共
 * @Author： tanyp
 * @Date： 2022/2/11 9:50
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Enums {

    /**
     * 系统字典类型
     */
    SYS_DIC_TYPE(0, "dic_type", "系统类"),
    SERVE_DIC_TYPE(1, "dic_type", "业务类"),

    /**
     * 认证相关
     */
    AUTH_NO_TOKEN(401, "auth_type", "用户凭证已过期，请重新登录！"),
    AUTH_NO_ACCESS(403, "auth_type", "无访问权限，请核实!"),
    AUTH_NONEXISTENT(404, "auth_type", "请求路径不存在"),

    /**
     * 公共
     */
    NOT_DEL_FALG(0, "del_falg", "正常"),
    IS_DEL_FALG(1, "del_falg", "已删除"),
    DISABLE_FALG(2, "del_falg", "已禁用"),

    /**
     * 用户信息相关
     */
    USER_SEX_MALE(0, "user_sex", "男"),
    USER_SEX_GIRL(1, "user_sex", "女"),
    USER_TYPE_ORDINARY(0, "user_type", "普通用户"),
    USER_TYPE_ADMINISTRATORS(1, "user_type", "管理员"),

    /**
     * 消息状态
     */
    MESSAGE_UNTREATED(0, "message_status", "未处理"),
    MESSAGE_PROCESSED(1, "message_status", "已处理"),

    /**
     * 菜单类型
     */
    MENU_TYPE_0(0, "menu_type", "按钮"),
    MENU_TYPE_1(1, "menu_type", "菜单"),

    /**
     * 状态
     */
    STATUS_0(0, "status", "未启用"),
    STATUS_1(1, "status", "启用"),

    ;

    private Integer key;
    private String group;
    private String value;


    /**
     * @author: tanyp
     * @Date: 2022/2/28
     * @Description:根据key获取value
     */
    public static String getValue(Integer key) {
        for (Enums item : Enums.values()) {
            if (Objects.equals(key, item.key)) {
                return item.getValue();
            }
        }
        return null;
    }


    /**
     * @author: tanyp
     * @Date: 2022/2/28
     * @Description:根据key和group获取value
     */
    public static String getVlaueByGroup(Integer key, String group) {
        for (Enums item : Enums.values()) {
            if (Objects.equals(key, item.key) && Objects.equals(group, item.group)) {
                return item.getValue();
            }
        }
        return null;
    }

}