package com.tansci.common.amqp;

import com.tansci.common.amqp.common.DetailRes;
import com.tansci.common.amqp.common.MessageWithTime;

/**
 * @ClassName： MessageSender.java
 * @ClassPath： com.tansci.common.amqp.MessageSender.java
 * @Description： 消息生产者
 * @Author： tanyp
 * @Date： 2022/9/23 15:56
 **/
public interface MessageSender {

    DetailRes send(Object message);

    DetailRes send(MessageWithTime messageWithTime);

}