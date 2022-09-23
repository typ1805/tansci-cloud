## 使用示例

**Model示例**

```java

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMessage {
    int id;
    String name;
}
```

**Sender示例**

```java

@Service
public class SenderExample {
    private static final String EXCHANGE = "example";
    private static final String ROUTING = "user-example";
    private static final String QUEUE = "user-example";

    @Autowired
    ConnectionFactory connectionFactory;

    private MessageSender messageSender;

    @PostConstruct
    public void init() throws IOException, TimeoutException {
        MQAccessBuilder mqAccessBuilder = new MQAccessBuilder(connectionFactory);
        messageSender = mqAccessBuilder.buildMessageSender(EXCHANGE, ROUTING, QUEUE);
    }

    public DetailRes send(UserMessage userMessage) {
        return messageSender.send(userMessage);
    }
}
```

**MessageProcess示例，本例中我们只是简单的将信息打印出来**

```java
public class UserMessageProcess implements MessageProcess<UserMessage> {

    @Override
    public DetailRes process(UserMessage userMessage) {
        System.out.println(userMessage);
        return new DetailRes(true, "");
    }
}
```

**Consumer示例**

```java

@Service
public class ConsumerExample {
    private static final String EXCHANGE = "example";
    private static final String ROUTING = "user-example";
    private static final String QUEUE = "user-example";

    @Autowired
    ConnectionFactory connectionFactory;

    private MessageConsumer messageConsumer;

    @PostConstruct
    public void init() throws IOException, TimeoutException {
        MQAccessBuilder mqAccessBuilder = new MQAccessBuilder(connectionFactory);
        messageConsumer = mqAccessBuilder.buildMessageConsumer(EXCHANGE, ROUTING, QUEUE, new UserMessageProcess());
    }

    public DetailRes consume() {
        return messageConsumer.consume();
    }
}
```

**线程池Consumer示例**

在main函数中，我们使用一个独立线程发送数据，并使用线程池接收数据。

```java

@Service
public class PoolExample {
    private static final String EXCHANGE = "example";
    private static final String ROUTING = "user-example";
    private static final String QUEUE = "user-example";

    @Autowired
    ConnectionFactory connectionFactory;

    private ThreadPoolConsumer<UserMessage> threadPoolConsumer;

    @PostConstruct
    public void init() {
        MQAccessBuilder mqAccessBuilder = new MQAccessBuilder(connectionFactory);
        MessageProcess<UserMessage> messageProcess = new UserMessageProcess();

        threadPoolConsumer = new ThreadPoolConsumer.ThreadPoolConsumerBuilder<UserMessage>()
                .setThreadCount(Constants.THREAD_COUNT).setIntervalMils(Constants.INTERVAL_MILS)
                .setExchange(EXCHANGE).setRoutingKey(ROUTING).setQueue(QUEUE)
                .setMQAccessBuilder(mqAccessBuilder).setMessageProcess(messageProcess)
                .build();
    }

    public void start() throws IOException {
        threadPoolConsumer.start();
    }

    public void stop() {
        threadPoolConsumer.stop();
    }
}
```

```java
public class PoolTestExample {

    @Autowired
    PoolExample senderExample;

    public void test() {
        senderExample.start();
        new Thread(new Runnable() {
            int id = 0;

            @Override
            public void run() {
                while (true) {
                    senderExample.send(new UserMessage(id++, "" + System.nanoTime()));
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
```