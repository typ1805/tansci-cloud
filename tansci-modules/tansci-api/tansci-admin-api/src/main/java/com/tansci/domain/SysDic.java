package com.tansci.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_dic")
@ApiModel(value = "数据字典")
public class SysDic {

    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键id", hidden = true)
    private String id;

    @ApiModelProperty(value = "父ID")
    private String parentId;

    @ApiModelProperty(value = "分组名称")
    private String groupName;

    @ApiModelProperty(value = "类型：0、系统，1、业务")
    private Integer type;

    @TableField(exist = false)
    @ApiModelProperty(value = "类型名称")
    private String typeName;

    @ApiModelProperty(value = "值")
    private Integer dicValue;

    @ApiModelProperty(value = "名称")
    private String dicLabel;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "预留字段1")
    private String text1;

    @ApiModelProperty(value = "预留字段3")
    private String text2;

    @ApiModelProperty(value = "预留字段2")
    private String text3;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "描述")
    private String remarks;

    @TableField(exist = false)
    private List<SysDic> children;

}
