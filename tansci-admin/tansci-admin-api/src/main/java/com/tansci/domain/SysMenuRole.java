package com.kuiper.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName： SysMenuRole.java
 * @ClassPath： com.kuiper.domain.SysMenuRole.java
 * @Description： 菜单角色
 * @Author： tanyp
 * @Date： 2022/4/25 16:09
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_menu_role")
@ApiModel(value = "菜单角色")
public class SysMenuRole implements Serializable {

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "菜单id")
    private String menuId;

    @ApiModelProperty(value = "当前角色低")
    @TableField(exist = false)
    private String thisRoleId;

}
