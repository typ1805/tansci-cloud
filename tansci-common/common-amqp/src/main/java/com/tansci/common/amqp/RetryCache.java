package com.tansci.common.amqp;

import com.tansci.common.amqp.common.Constants;
import com.tansci.common.amqp.common.DetailRes;
import com.tansci.common.amqp.common.MessageWithTime;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName： RetryCache.java
 * @ClassPath： com.tansci.common.amqp.RetryCache.java
 * @Description： 重试缓存
 * @Author： tanyp
 * @Date： 2022/9/23 15:56
 **/
@Slf4j
public class RetryCache {
    private MessageSender sender;
    private boolean stop = false;
    private Map<Long, MessageWithTime> map = new ConcurrentSkipListMap<>();
    private AtomicLong id = new AtomicLong();

    public void setSender(MessageSender sender) {
        this.sender = sender;
        startRetry();
    }

    public long generateId() {
        return id.incrementAndGet();
    }

    public void add(MessageWithTime messageWithTime) {
        map.putIfAbsent(messageWithTime.getId(), messageWithTime);
    }

    public void del(long id) {
        map.remove(id);
    }

    private void startRetry() {
        new Thread(() -> {
            while (!stop) {
                try {
                    Thread.sleep(Constants.RETRY_TIME_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                long now = System.currentTimeMillis();
                for (Map.Entry<Long, MessageWithTime> entry : map.entrySet()) {
                    MessageWithTime messageWithTime = entry.getValue();
                    if (null != messageWithTime) {
                        if (messageWithTime.getTime() + 3 * Constants.VALID_TIME < now) {
                            log.info("send message {} failed after 3 min ", messageWithTime);
                            del(entry.getKey());
                        } else if (messageWithTime.getTime() + Constants.VALID_TIME < now) {
                            DetailRes res = sender.send(messageWithTime);
                            if (!res.isSuccess()) {
                                log.info("retry send message failed {} errMsg {}", messageWithTime, res.getErrMsg());
                            }
                        }
                    }
                }
            }
        }).start();
    }

}