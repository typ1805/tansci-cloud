package com.kuiper.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName： SysRoleDto.java
 * @ClassPath： com.kuiper.dto.SysRoleDto.java
 * @Description： 角色DTO
 * @Author： tanyp
 * @Date： 2022/2/22 10:25
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "角色DTO")
public class SysRoleDto implements Serializable {

    @ApiModelProperty(value = "角色id")
    private String id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "组织id")
    private String orgId;

    @ApiModelProperty(value = "菜单IDS")
    private List<String> menuIds;

}
