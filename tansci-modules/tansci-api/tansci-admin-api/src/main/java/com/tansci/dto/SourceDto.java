package com.tansci.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName： SourceDto.java
 * @ClassPath： com.tansci.dto.SourceDto.java
 * @Description： 数据源信息DTO
 * @Author： tanyp
 * @Date： 2022/6/13 10:44
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("数据源信息DTO")
public class SourceDto implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "数据库名称")
    private String name;

    @ApiModelProperty(value = "表名称")
    private String tableName;

    @ApiModelProperty(value = "数据源类型")
    private String dbType;

    @ApiModelProperty(value = "状态")
    private String status;

}
