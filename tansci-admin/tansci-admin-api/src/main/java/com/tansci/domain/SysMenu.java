package com.tansci.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName： SysUserDto.java
 * @ClassPath： com.tansci.dto.SysUserDto.java
 * @Description： 用户实体
 * @Author： tanyp
 * @Date： 2022/04/22 11:33
 **/
@Data
@TableName("sys_menu")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "菜单")
public class SysMenu implements Serializable {

    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键id", hidden = true)
    private String id;

    @ApiModelProperty(value = "菜单编码")
    private String code;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "父菜单ID")
    private String parentId;

    @ApiModelProperty(value = "状态：0：删除，1：正常，2：禁用")
    private Integer status;

    @ApiModelProperty(value = "菜单UR")
    private String url;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "级别")
    private Integer level;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "描述")
    private String remarks;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "子级菜单")
    private List<SysMenu> children;

    @TableField(exist = false)
    @ApiModelProperty(value = "用户已选择菜单")
    private List<String> menuIds;

}
