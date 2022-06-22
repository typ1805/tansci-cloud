package com.tansci.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @path：com.tansci.auth.domain.UserVo.java
 * @className：UserVo.java
 * @description：用户信息
 * @author：tanyp
 * @dateTime：2022/2/11 10:38
 * @editNote：
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

    private String username;

    private String token;

    private String loginTime;

}
