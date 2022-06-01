package com.tansci.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @ClassName： HomeDto.java
 * @ClassPath： com.tansci.dto.HomeDto.java
 * @Description： 首页DTO
 * @Author： tanyp
 * @Date： 2022/6/1 15:38
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "首页DTO")
public class HomeDto implements Serializable {

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("时间")
    private LocalDate date;

}
