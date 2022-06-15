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

/**
 * @ClassName： DataSource.java
 * @ClassPath： com.tansci.domain.DataSource.java
 * @Description： 数据源
 * @Author： tanyp
 * @Date： 2022/5/31 15:05
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "data_source")
@ApiModel(value = "数据源")
public class DataSource implements Serializable {

    @ApiModelProperty("主键ID")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty("数据库名称")
    private String name;

    @ApiModelProperty("状态：0、未启用，1、启用，1、禁用")
    private Integer status;
    @TableField(exist = false)
    @ApiModelProperty("状态")
    private String statusName;

    @ApiModelProperty("类型：1、mysql，2、MariaDB，3、Oracle11g，4、Oracle12c+，5、PostgreSql，6、SQLServer2008，7、SQLServer2012+，8、其他数据库")
    private Integer type;
    @TableField(exist = false)
    @ApiModelProperty("类型")
    private String typeName;

    @ApiModelProperty("主机")
    private String host;

    @ApiModelProperty("端口")
    private String port;

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("备注")
    private String remarks;

}
