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

    private String id;

    private String username;

    private String nickname;

    private Integer type;

}
