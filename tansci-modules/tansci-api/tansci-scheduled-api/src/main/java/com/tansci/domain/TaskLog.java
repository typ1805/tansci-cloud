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
 * @ClassName： TaskLog.java
 * @ClassPath： com.tansci.domain.TaskLog.java
 * @Description： 调度执行日志
 * @Author： tanyp
 * @Date： 2022/6/9 16:36
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "task_log")
@ApiModel(value = "调度执行日志")
public class TaskLog implements Serializable {

    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "调度id")
    private String taskId;

    @TableField(exist = false)
    @ApiModelProperty(value = "调度名称")
    private String taskName;

    @TableField(exist = false)
    @ApiModelProperty(value = "任务编码")
    private String taskCode;

    @TableField(exist = false)
    @ApiModelProperty(value = "创建人")
    private String creater;

    @ApiModelProperty(value = "任务服务名称")
    private String serverName;

    @ApiModelProperty(value = "状态：0、成功，1、失败")
    private Integer status;
    @TableField(exist = false)
    @ApiModelProperty(value = "状态")
    private String statusName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @ApiModelProperty(value = "执行时间")
    private LocalDateTime executionTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "描述")
    private String remarks;

}
