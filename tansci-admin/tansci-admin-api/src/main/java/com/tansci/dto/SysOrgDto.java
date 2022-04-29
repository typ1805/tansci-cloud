package com.kuiper.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName： SysOrgDto.java
 * @ClassPath： com.kuiper.dto.SysOrgDto.java
 * @Description： 组织DTO
 * @Author： tanyp
 * @Date： 2022/2/22 10:25
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "组织DTO")
public class SysOrgDto implements Serializable {

    @ApiModelProperty(value = "组织id")
    private String id;

    @ApiModelProperty(value = "组织名称")
    private String name;

    @ApiModelProperty(value = "父组织ID")
    private String parentId;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "用户id")
    private String userId;

}
