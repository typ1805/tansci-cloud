package com.tansci.admin.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @ClassName： SysDicDto.java
 * @ClassPath： com.kuiper.admin.api.dto.SysDicDto.java
 * @Description： 字典DTO
 * @Author： tanyp
 * @Date： 2022/2/22 10:25
 **/
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "字典DTO")
public class SysDicDto implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "父ID")
    private String parentId;

    @ApiModelProperty(value = "分组名称")
    private String groupName;

    @ApiModelProperty(value = "类型：0、系统，1、业务")
    private Integer type;

    @ApiModelProperty(value = "值")
    private Integer value;

    @ApiModelProperty(value = "名称")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sort;

}