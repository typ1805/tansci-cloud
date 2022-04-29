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
 * @ClassName： SysOrgRole.java
 * @ClassPath： com.kuiper.domain.SysOrgRole.java
 * @Description： 组织角色
 * @Author： tanyp
 * @Date： 2022/4/25 16:09
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_org_role")
@ApiModel(value = "组织角色")
public class SysOrgRole implements Serializable {

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "组织id")
    private String orgId;

}
