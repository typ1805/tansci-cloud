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
 * @ClassName： SysUserOrg.java
 * @ClassPath： com.kuiper.domain.SysUserOrg.java
 * @Description： 用户组织
 * @Author： tanyp
 * @Date： 2022/4/25 16:09
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user_org")
@ApiModel(value = "用户组织")
public class SysUserOrg implements Serializable {

    @ApiModelProperty(value = "组织ID")
    private String orgId;

    @ApiModelProperty(value = "用户id")
    private String userId;

}
