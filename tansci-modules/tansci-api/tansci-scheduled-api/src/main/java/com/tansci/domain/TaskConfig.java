package com.tansci.domain;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @ClassName： TaskConfig.java
 * @ClassPath： com.kuiper.tansci.TaskConfig.java
 * @Description： 调度配置
 * @Author： tanyp
 * @Date： 2022/6/9 16:36
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "task_config")
@ApiModel(value = "调度配置")
public class TaskConfig implements Serializable {

    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "任务服务名称")
    private String serverName;

    @ApiModelProperty(value = "任务名称")
    private String name;

    @ApiModelProperty(value = "任务编码")
    private String taskCode;

    @ApiModelProperty(value = "任务执行规则时间：cron表达式")
    private String expression;

    @ApiModelProperty(value = "状态：0、未启动，1、正常")
    private Integer status;

    @ApiModelProperty(value = "创建人")
    private String creater;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "描述")
    private String remarks;

}
