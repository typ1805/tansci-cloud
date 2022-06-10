package com.tansci.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName： TaskConfigDto.java
 * @ClassPath： com.tansci.dto.TaskConfigDto.java
 * @Description： 调度配置
 * @Author： tanyp
 * @Date： 2022/6/9 16:46
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "调度配置")
public class TaskConfigDto implements Serializable {

    @TableId(type = IdType.ASSIGN_UUID)
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

}
