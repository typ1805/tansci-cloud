package com.tansci.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName： SysMenuDto.java
 * @ClassPath： com.kuiper.dto.SysMenuDto.java
 * @Description： 菜单DTO
 * @Author： tanyp
 * @Date： 2022/2/22 10:25
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "菜单DTO")
public class SysMenuDto implements Serializable {

    @ApiModelProperty(value = "菜单id")
    private String id;

    @ApiModelProperty(value = "菜单编码")
    private String code;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "父菜单ID")
    private String parentId;

    @ApiModelProperty(value = "状态：0：删除，1：正常，2：禁用")
    private Integer status;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "用户id")
    private String userId;

}
