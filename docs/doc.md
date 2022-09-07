## Tansci Cloud 系统

Tansci Cloud 基于 SpringCloud Alibaba + Vue3.2 + Element Plus 的微服务项目。

此项目包含开发分布式应用微服务的必需组件，方便开发者通过 Spring Cloud 编程模型轻松使用这些组件来开发分布式应用服务。 

依托 Spring Cloud Alibaba，您只需要添加一些注解和少量配置，就可以将 Spring Cloud 应用接入阿里微服务解决方案，通过阿里中间件来迅速搭建分布式应用系统。

此项目分为微服务版和单体版：

单体版：
- Gitee: [https://gitee.com/typ1805/tansci](https://gitee.com/typ1805/tansci)
- GitHub：[https://github.com/typ1805/tansci](https://github.com/typ1805/tansci)

**SpringCloud架构图**

![SpringCloud架构图](image/SpringCloudAlibaba.png)

> 架构功能正在完善中...

## 环境要求

| 名称 | 版本号 | 描述 |
| ---- | ---- | ---- |
| JDK | 1.8+ | 强制要求 |
| mysql | 5.7+ | 数据库 |
| redis | 5.0+ | NOSQL数据库 |
| Nginx | 1.16+ | 请求转发、反向代理 |
| Maven | 3.8+ | 项目构建，管理 |
| Git | 2.14+ | 项目版本管理 |
| Node | 14.16+ | 前端环境要求 |

## 项目目录

<pre>
├─docs                            # 文档，包含SQL脚本
├─tansci-api                      # 模块 API
├─tansci-auth                     # 安全认证
├─tansci-common                   # 公共模块
│  ├─common-core                  # 公共核心包
│  ├─common-database              # 公共动态数据源包
│  └─common-web                   # 公共WEB包
│  └─common-redisson              # 分布式锁
├─tansci-gateway                  # 网关
├─tansci-modules                  # 模块 Service
│  ├─tansci-admin-service
│  └─tansci-scheduled-service
└─tansci-view                     # 前端项目目录
    ├─public
    └─src
        ├─api                     # 服务AIP配置
        ├─assets                  # 静态资源
        ├─components              # 公共组件
        ├─router                  # 路由
        ├─store                   # Pinia 封装
        ├─styles                  # 公共样式
        ├─types                   # ts
        ├─utils                   # 工具包
        └─views                   # 功能模块
</pre>

----

## 项目启动顺序

**启动后端**

创建数据库，执行 ``docs/sql/tansci_cloud.sql、docs/sql/tansci_config.sql`` SQL初始化数据；

模块启动顺序：

- TansciAdminServiceApplication
- TansciAuthApplication
- TansciGatewayApplication
- TansciScheduledServiceApplication

> 其余模块可任意启动

**启动前端**
```txt
npm install  # 初始化项目

npm run dev  # 启动项目
```

项目启动成功后访问： [http://localhost:3000](http://localhost:3000)

> 测试用户 admin / admin123

----


## Nacos 配置

下载地址：[https://github.com/alibaba/nacos/releases](https://github.com/alibaba/nacos/releases)

> 示例为 nacos 2.0.4 版本

#### 1、Windows环境

修改配置文件 `nacos-server-2.0.4/conf/application.properties`

```yml
#*************** Config Module Related Configurations ***************#
### If use MySQL as datasource:
spring.datasource.platform=mysql

### Count of DB:
db.num=1

### Connect URL of DB:
db.url.0=jdbc:mysql://127.0.0.1:3306/tansci_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
db.user.0=root
db.password.0=root
```

执行 `nacos-server-2.0.4/bin/startup.cmd` 即可启动 Nacos 。

#### 2、Linux环境

```shell
# linux
cd /usr/local/nacos/bin
./startup.sh -m standalone
```

浏览器访问： [http://localhost:8848/nacos](http://localhost:8848/nacos)

> 默认用户名密码 `nacos/nacos`
> 
> 如果使用测试环境做注册中心，请创建自己的命名空间注册服务，避免多人混用。

## Sentinel 配置

下载地址：[https://github.com/alibaba/Sentinel/releases](https://github.com/alibaba/Sentinel/releases)

> 示例为 sentinel 1.8.3 版本

执行命令运行：

```shell
# windows
java -Dserver.port=8858 -Dcsp.sentinel.dashboard.server=localhost:8718 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.3.jar

# linux
nohup  java -Dserver.port=8858 -Dcsp.sentinel.dashboard.server=localhost:8718 -Dproject.name=sentinel-dashboard -jar /usr/local/sentinel/sentinel-dashboard-1.8.3.jar &

```

浏览器访问： [http://localhost:8080](http://localhost:8080)

> 默认用户名密码 `sentinel/sentinel`
>
> 其中 -Dserver.port=8080 用于指定 Sentinel 控制台端口为 8080。
>
> 注意：启动 Sentinel 控制台需要 JDK 版本为 1.8 及以上版本。

----

## Seata 配置

官网：[https://seata.io](https://seata.io/zh-cn/)

下载地址：[https://seata.io/zh-cn/blog/download.html](https://seata.io/zh-cn/blog/download.html)

> 示例为 seata 1.5.2 版本

**修改配置**

修改 conf 下的 `application.yml` 配置文件:

```yaml
server:
  port: 7091

spring:
  application:
    name: seata-server

logging:
  config: classpath:logback-spring.xml
  file:
    path: ${user.home}/logs/seata
  extend:
    logstash-appender:
      destination: 127.0.0.1:4560
    kafka-appender:
      bootstrap-servers: 127.0.0.1:9092
      topic: logback_to_logstash

console:
  user:
    username: seata
    password: seata

seata:
  config:
    # support: nacos, consul, apollo, zk, etcd3
    type: nacos
    nacos:
      server-addr: 127.0.0.1:8848
      namespace: d01a5728-d581-438a-a0e8-e180a08f1e6d
      group: SEATA_GROUP
      username: nacos
      password: nacos
      data-id: seataServer.properties
  registry:
    # support: nacos, eureka, redis, zk, consul, etcd3, sofa
    type: nacos
    preferred-networks: 30.240.*
    nacos:
      application: seata-server
      server-addr: 127.0.0.1:8848
      group: SEATA_GROUP
      namespace: d01a5728-d581-438a-a0e8-e180a08f1e6d
      cluster: default
      username: nacos
      password: nacos
  store: #这个配置作用不大,因为上面在引入的nacos配置的时候,又会再引入一遍数据库的配置
    # support: file 、 db 、 redis
    mode: db
    db:
      datasource: druid
      db-type: mysql
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://127.0.0.1:3306/tansci_seata?rewriteBatchedStatements=true
      user: root
      password: weqfd4312
      min-conn: 5
      max-conn: 100
      global-table: global_table
      branch-table: branch_table
      lock-table: lock_table
      distributed-lock-table: distributed_lock
      query-limit: 100
      max-wait: 5000
#  server:
#    service-port: 8091 #If not configured, the default is '${server.port} + 1000'
  security:
    secretKey: SeataSecretKey0c382ef121d778043159209298fd40bf3850a017
    tokenValidityInMilliseconds: 1800000
    ignore:
      urls: /,/**/*.css,/**/*.js,/**/*.html,/**/*.map,/**/*.svg,/**/*.png,/**/*.ico,/console-fe/public/**,/api/v1/auth/login
```

初始化数据库 `docs/sql/tansci_seate.sql`.

**Nacos配置**

1.添加配置 `service.vgroupMapping.default_tx_group`
```yaml
Data ID： service.vgroupMapping.default_tx_group

Group：SEATA_GROUP

# 配置内容：
default
```
2.添加配置 `seataServer.properties`
```yaml
Data ID：seataServer.properties

Group：SEATA_GROUP

# 配置内容

# For details about configuration items, see https://seata.io/zh-cn/docs/user/configurations.html
# Transport configuration, for client and server
transport.type=TCP
transport.server=NIO
transport.heartbeat=true
transport.enableTmClientBatchSendRequest=false
transport.enableRmClientBatchSendRequest=true
transport.enableTcServerBatchSendResponse=false
transport.rpcRmRequestTimeout=30000
transport.rpcTmRequestTimeout=30000
transport.rpcTcRequestTimeout=30000
transport.threadFactory.bossThreadPrefix=NettyBoss
transport.threadFactory.workerThreadPrefix=NettyServerNIOWorker
transport.threadFactory.serverExecutorThreadPrefix=NettyServerBizHandler
transport.threadFactory.shareBossWorker=false
transport.threadFactory.clientSelectorThreadPrefix=NettyClientSelector
transport.threadFactory.clientSelectorThreadSize=1
transport.threadFactory.clientWorkerThreadPrefix=NettyClientWorkerThread
transport.threadFactory.bossThreadSize=1
transport.threadFactory.workerThreadSize=default
transport.shutdown.wait=3
transport.serialization=seata
transport.compressor=none

# Transaction routing rules configuration, only for the client
service.vgroupMapping.default_tx_group=default
# If you use a registry, you can ignore it
service.default.grouplist=127.0.0.1:8091 #修改为线上IP:PORT
service.enableDegrade=false
service.disableGlobalTransaction=false

# Transaction rule configuration, only for the client
client.rm.asyncCommitBufferLimit=10000
client.rm.lock.retryInterval=10
client.rm.lock.retryTimes=30
client.rm.lock.retryPolicyBranchRollbackOnConflict=true
client.rm.reportRetryCount=5
client.rm.tableMetaCheckEnable=true
client.rm.tableMetaCheckerInterval=60000
client.rm.sqlParserType=druid
client.rm.reportSuccessEnable=false
client.rm.sagaBranchRegisterEnable=false
client.rm.sagaJsonParser=fastjson
client.rm.tccActionInterceptorOrder=-2147482648
client.tm.commitRetryCount=5
client.tm.rollbackRetryCount=5
client.tm.defaultGlobalTransactionTimeout=60000
client.tm.degradeCheck=false
client.tm.degradeCheckAllowTimes=10
client.tm.degradeCheckPeriod=2000
client.tm.interceptorOrder=-2147482648
client.undo.dataValidation=true
client.undo.logSerialization=jackson
client.undo.onlyCareUpdateColumns=true
server.undo.logSaveDays=7
server.undo.logDeletePeriod=86400000
client.undo.logTable=undo_log
client.undo.compress.enable=true
client.undo.compress.type=zip
client.undo.compress.threshold=64k
# For TCC transaction mode
tcc.fence.logTableName=tcc_fence_log
tcc.fence.cleanPeriod=1h

# Log rule configuration, for client and server
log.exceptionRate=100

# Transaction storage configuration, only for the server. The file, DB, and redis configuration values are optional.
store.mode=file
store.lock.mode=file
store.session.mode=file
# Used for password encryption
store.publicKey=

# If `store.mode,store.lock.mode,store.session.mode` are not equal to `file`, you can remove the configuration block.
store.file.dir=file_store/data
store.file.maxBranchSessionSize=16384
store.file.maxGlobalSessionSize=512
store.file.fileWriteBufferCacheSize=16384
store.file.flushDiskMode=async
store.file.sessionReloadReadSize=100

# These configurations are required if the `store mode` is `db`. If `store.mode,store.lock.mode,store.session.mode` are not equal to `db`, you can remove the configuration block.
store.db.datasource=druid
store.db.dbType=mysql
store.db.driverClassName=com.mysql.jdbc.Driver
store.db.url=jdbc:mysql://127.0.0.1:3306/seata?useUnicode=true&rewriteBatchedStatements=true #修改为线上IP:PORT
store.db.user=root #修改
store.db.password=root #修改
store.db.minConn=5
store.db.maxConn=30
store.db.globalTable=global_table
store.db.branchTable=branch_table
store.db.distributedLockTable=distributed_lock
store.db.queryLimit=100
store.db.lockTable=lock_table
store.db.maxWait=5000

# These configurations are required if the `store mode` is `redis`. If `store.mode,store.lock.mode,store.session.mode` are not equal to `redis`, you can remove the configuration block.
store.redis.mode=single
store.redis.single.host=127.0.0.1
store.redis.single.port=6379
store.redis.sentinel.masterName=
store.redis.sentinel.sentinelHosts=
store.redis.maxConn=10
store.redis.minConn=1
store.redis.maxTotal=100
store.redis.database=0
store.redis.password=
store.redis.queryLimit=100

# Transaction rule configuration, only for the server
server.recovery.committingRetryPeriod=1000
server.recovery.asynCommittingRetryPeriod=1000
server.recovery.rollbackingRetryPeriod=1000
server.recovery.timeoutRetryPeriod=1000
server.maxCommitRetryTimeout=-1
server.maxRollbackRetryTimeout=-1
server.rollbackRetryTimeoutUnlockEnable=false
server.distributedLockExpireTime=10000
server.xaerNotaRetryTimeout=60000
server.session.branchAsyncQueueSize=5000
server.session.enableBranchAsyncRemove=false
server.enableParallelRequestHandle=false

# Metrics configuration, only for the server
metrics.enabled=false
metrics.registryType=compact
metrics.exporterList=prometheus
metrics.exporterPrometheusPort=9898

```

**启动**

```shell
# Windows
进到/bin目录下, 执行 seata-server.bat 即可启动

# linux
cd /bin
./seata-server.sh
```

访问：[http://localhost:7091](http://localhost:7091)， `seata/seata`

**使用**

在需要使用到分布式事务的服务数据库创建undo_log表， 加上注解：@GlobalTransactional(rollbackFor = Exception.class)即可。

```sql
CREATE TABLE `undo_log` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `branch_id` bigint(20) NOT NULL,
    `xid` varchar(100) NOT NULL,
    `context` varchar(128) NOT NULL,
    `rollback_info` longblob NOT NULL,
    `log_status` int(11) NOT NULL,
    `log_created` datetime NOT NULL,
    `log_modified` datetime NOT NULL,
    `ext` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
```
增加配置：
```yaml
# seata 配置
seata:
  enable: true
  application-id: seata-admin-service
  tx-service-group: default_tx_group
  registry:
    type: nacos
    nacos:
      application: seata-server # seata 服务名
      # 非本地请修改具体的地址
      server-addr: ${NACOS-HOST:tansci-nacos}:${NACOS-PORT:8848}
      group: SEATA_GROUP
  config:
    type: nacos
    nacos:
      # nacos ip地址
      server-addr: ${NACOS-HOST:tansci-nacos}:${NACOS-PORT:8848}
      group: SEATA_GROUP
      data-id: "seataServer.properties"
```

----

## API文档

API 文档访问地址 http://网关ip:网关端口/doc.html

例如：[http://127.0.0.1:9001/doc.html](http://127.0.0.1:9001/doc.html)

## 登录认证

**安全认证登录示例：**

- 请求地址：[http://localhost:8001/auth/login](http://localhost:8001/auth/login)
- 请求方式：POST
- 请求参数：
```json
{
  "username": "admin",
  "password": "admin123"
}
```
- 返回参数：
```json
{
  "code": 200,
  "fail": false,
  "message": "操作成功",
  "result": {
    "loginTime": "2022-02-16 11:58:29",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdWJqZWN0IiwiZXhwIjoxNjQ0OTkxMTA4LCJwYXlsb2FkIjoie1wiYWNjb3VudE5vbkV4cGlyZWRcIjp0cnVlLFwiYWNjb3VudE5vbkxvY2tlZFwiOnRydWUsXCJhdXRob3JpdGllc1wiOlt7XCJhdXRob3JpdHlcIjpcIlJPTEVfTUVSQ0hBTlRTXCJ9XSxcImNyZWRlbnRpYWxzTm9uRXhwaXJlZFwiOnRydWUsXCJlbmFibGVkXCI6dHJ1ZSxcImlkXCI6XCJiYzNhYzI2ZTY5NzMxYjYxN2ViODAyNzQ0NTNmNmRiYVwiLFwicGFzc3dvcmRcIjpcIiQyYSQxMCR0bFdXZmpUT2JxTHNDNk9OcmhOTFwvLkdJcEFvRnUyMDVUWFBLNnhVUEhIcjFrQVwvcGFLNGxxXCIsXCJ1c2VybmFtZVwiOlwiYWRtaW5cIn0ifQ.8fXiQqlP9SLJK-_sPxws98VrUbDs5kvFysPmn3-Aqu8",
    "username": "admin"
  },
  "success": true
}
```
- 设置Headers
```text
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdWJqZWN0IiwiZXhwIjoxNjQ0ODk4NjY0LCJwYXlsb2FkIjoie1wiYWNjb3VudE5vbkV4cGlyZWRcIjp0cnVlLFwiYWNjb3VudE5vbkxvY2tlZFwiOnRydWUsXCJhdXRob3JpdGllc1wiOlt7XCJhdXRob3JpdHlcIjpcIlJPTEVfTUVSQ0hBTlRTXCJ9XSxcImNyZWRlbnRpYWxzTm9uRXhwaXJlZFwiOnRydWUsXCJlbmFibGVkXCI6dHJ1ZSxcImlkXCI6XCJiYzNhYzI2ZTY5NzMxYjYxN2ViODAyNzQ0NTNmNmRiYVwiLFwicGFzc3dvcmRcIjpcIiQyYSQxMCR0bFdXZmpUT2JxTHNDNk9OcmhOTFwvLkdJcEFvRnUyMDVUWFBLNnhVUEhIcjFrQVwvcGFLNGxxXCIsXCJ1c2VybmFtZVwiOlwiYWRtaW5cIn0ifQ.54GyxZs77ADpE_KYzdy8EAPOLYWL4AqcJDQId_SYCok
```

## 联系作者

- QQ：742354529
- QQ群：747200630
- 个人主站：[http://tansci.top](http://tansci.top)、[https://typ1805.gitee.io](https://typ1805.gitee.io)
- 关注公众号

![个人公众号](image/gzh.jpg)