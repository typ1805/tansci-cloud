package com.kuiper.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @path：com.kuiper.vo.SysMenuRoleVo.java
 * @className：SysMenuRoleVo.java
 * @description：菜单权限
 * @author：tanyp
 * @dateTime：2022/04/23 12:32
 * @editNote：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "菜单权限VO")
public class SysMenuRoleVo {

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "父id")
    private String parentId;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "菜单id")
    private String menuId;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "子集")
    private List<SysMenuRoleVo> children;

}