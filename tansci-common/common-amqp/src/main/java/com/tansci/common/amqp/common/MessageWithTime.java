package com.tansci.common.amqp.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName： MessageWithTime.java
 * @ClassPath： com.tansci.common.amqp.common.MessageWithTime.java
 * @Description： TODO
 * @Author： tanyp
 * @Date： 2022/9/23 15:55
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageWithTime {

    private long id;

    private long time;

    private Object message;

}
