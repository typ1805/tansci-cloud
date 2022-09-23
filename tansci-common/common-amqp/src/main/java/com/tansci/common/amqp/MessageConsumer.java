package com.tansci.common.amqp;

import com.tansci.common.amqp.common.DetailRes;

/**
 * @ClassName： MessageConsumer.java
 * @ClassPath： com.tansci.common.amqp.MessageConsumer.java
 * @Description： 消息消费者
 * @Author： tanyp
 * @Date： 2022/9/23 15:56
 **/
public interface MessageConsumer {

    DetailRes consume();

}