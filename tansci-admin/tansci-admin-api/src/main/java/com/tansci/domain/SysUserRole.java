package com.kuiper.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName： SysUserRole.java
 * @ClassPath： com.kuiper.domain.SysUserRole.java
 * @Description： 用户角色
 * @Author： tanyp
 * @Date： 2022/4/25 16:09
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user_role")
@ApiModel(value = "用户角色")
public class SysUserRole implements Serializable {

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "用户id")
    private String userId;

}
