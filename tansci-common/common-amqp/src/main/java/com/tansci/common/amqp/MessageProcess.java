package com.tansci.common.amqp;

import com.tansci.common.amqp.common.DetailRes;

/**
 * @ClassName： MessageProcess.java
 * @ClassPath： com.tansci.common.amqp.MessageProcess.java
 * @Description： 消息处理
 * @Author： tanyp
 * @Date： 2022/9/23 15:56
 **/
public interface MessageProcess<T> {

    DetailRes process(T message);

}