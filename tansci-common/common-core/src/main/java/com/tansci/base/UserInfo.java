package com.tansci.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName： UserInfo.java
 * @ClassPath： com.tansci.base.UserInfo.java
 * @Description： 存储用户信息
 * @Author： tanyp
 * @Date： 2022/2/22 16:00
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {

    // 用户id
    private String id;

    // 用户名称
    private String username;

    // 用户昵称
    private String nickname;

    // 用户类型
    private Integer type;

    // 权限ID
    private String roleId;

}
