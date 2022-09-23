package com.tansci.common.amqp;

import com.tansci.common.amqp.common.Constants;
import com.tansci.common.amqp.common.DetailRes;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName： ThreadPoolConsumer.java
 * @ClassPath： com.tansci.common.amqp.ThreadPoolConsumer.java
 * @Description： 多线程
 * @Author： tanyp
 * @Date： 2022/9/23 15:56
 **/
@Slf4j
public class ThreadPoolConsumer<T> {

    private ExecutorService executor;
    private volatile boolean stop = false;
    private final ThreadPoolConsumerBuilder<T> infoHolder;

    public static class ThreadPoolConsumerBuilder<T> {
        int threadCount;
        long intervalMils;
        MQAccessBuilder mqAccessBuilder;
        String exchange;
        String routingKey;
        String queue;
        String type = "direct";
        MessageProcess<T> messageProcess;

        public ThreadPoolConsumerBuilder<T> setThreadCount(int threadCount) {
            this.threadCount = threadCount;
            return this;
        }

        public ThreadPoolConsumerBuilder<T> setIntervalMils(long intervalMils) {
            this.intervalMils = intervalMils;
            return this;
        }

        public ThreadPoolConsumerBuilder<T> setMQAccessBuilder(MQAccessBuilder mqAccessBuilder) {
            this.mqAccessBuilder = mqAccessBuilder;
            return this;
        }

        public ThreadPoolConsumerBuilder<T> setExchange(String exchange) {
            this.exchange = exchange;
            return this;
        }

        public ThreadPoolConsumerBuilder<T> setRoutingKey(String routingKey) {
            this.routingKey = routingKey;
            return this;
        }

        public ThreadPoolConsumerBuilder<T> setQueue(String queue) {
            this.queue = queue;
            return this;
        }

        public ThreadPoolConsumerBuilder<T> setType(String type) {
            this.type = type;
            return this;
        }

        public ThreadPoolConsumerBuilder<T> setMessageProcess(MessageProcess<T> messageProcess) {
            this.messageProcess = messageProcess;
            return this;
        }

        public ThreadPoolConsumer<T> build() {
            return new ThreadPoolConsumer<T>(this);
        }
    }

    private ThreadPoolConsumer(ThreadPoolConsumerBuilder<T> threadPoolConsumerBuilder) {
        this.infoHolder = threadPoolConsumerBuilder;
        executor = Executors.newFixedThreadPool(threadPoolConsumerBuilder.threadCount);
    }

    /**
     * @MonthName： start
     * @Description：
     * 1、构造messageConsumer
     * 2、执行consume
     * @Author： tanyp
     * @Date： 2022/9/23 16:12
     * @Param： []
     * @return： void
     **/
    public void start() throws IOException {
        for (int i = 0; i < infoHolder.threadCount; i++) {
            // 构造messageConsumer
            final MessageConsumer messageConsumer = infoHolder.mqAccessBuilder.buildMessageConsumer(infoHolder.exchange, infoHolder.routingKey, infoHolder.queue, infoHolder.messageProcess, infoHolder.type);

            executor.execute(new Runnable() {
                @Override
                public void run() {
                    while (!stop) {
                        try {
                            // 执行consume
                            DetailRes detailRes = messageConsumer.consume();

                            if (infoHolder.intervalMils > 0) {
                                try {
                                    Thread.sleep(infoHolder.intervalMils);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                    log.info("interrupt ", e);
                                }
                            }

                            if (!detailRes.isSuccess()) {
                                log.info("run error " + detailRes.getErrMsg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.info("run exception ", e);
                        }
                    }
                }
            });
        }

        Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
    }

    public void stop() {
        this.stop = true;
        try {
            Thread.sleep(Constants.ONE_SECOND);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}