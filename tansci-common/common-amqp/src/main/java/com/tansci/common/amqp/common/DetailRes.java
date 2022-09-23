package com.tansci.common.amqp.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName： DetailRes.java
 * @ClassPath： com.tansci.common.amqp.common.DetailRes.java
 * @Description： 统一返回值, 可描述失败细节
 * @Author： tanyp
 * @Date： 2022/9/23 15:54
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailRes {

    boolean isSuccess;

    String errMsg;

}
