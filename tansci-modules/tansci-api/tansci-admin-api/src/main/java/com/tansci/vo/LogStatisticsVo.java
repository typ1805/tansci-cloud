package com.tansci.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName： LogStatisticsVo.java
 * @ClassPath： com.tansci.vo.LogStatisticsVo.java
 * @Description： 日志统计
 * @Author： tanyp
 * @Date： 2022/6/1 15:40
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "日志统计")
public class LogStatisticsVo implements Serializable {

    @ApiModelProperty("操作次数")
    private Integer operationNum;

    @ApiModelProperty("异常次数")
    private Integer errorNum;

    @ApiModelProperty("登录次数")
    private Integer loginNum;

}
