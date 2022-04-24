| 名称 | 描述 |
|----|----|
| 文档版本 | V1.0 |
| 编写人 | tanyp |
| 编写时间 | 2022-04-22 | 

## 简介

SpringCloud Alibaba 项目

## 架构

### 结构图

![./docs/image/SpringCloudAlibaba.png](./docs/image/SpringCloudAlibaba.png)

### 技术栈

**环境要求**

| 名称 | 版本号 | 描述 |
| ---- | ---- | ---- |
| JDK | 1.8+ | 强制要求 |
| mysql | 5.7+ | 数据库 |
| redis | 5.0+ | NOSQL数据库 |
| Nginx | 1.16+ | 请求转发、反向代理 |
| Maven | 3.8+ | 项目构建，管理 |
| Git | 2.14+ | 项目版本管理 |

**技术**

| 名称 | 版本号 | 描述 |
| ---- | ---- | ---- |
| SpringBoot | 2.6.3 | 主架构 |
| SpringCloud | 2021.0.1 | 主架构 |
| SpringCloud Alibaba | 2021.0.1.0 | 主架构 |
| Mybatis Plus | 3.5.1 | 数据层 |
| Druid | 1.2.6 | 连接池 |
| Spring Security | -- | 权限认证 |
| Fastjson | 1.2.62 | -- |
| nimbus-jose-jwt | 9.1.1 | 安全认证 |
| Lombok | 1.18.12 | -- |
| knife4j | 3.0.3 | API文档 |

## API文档

API 文档访问地址 http://网关ip:网关端口/doc.html

例如：[http://127.0.0.1:8001/doc.html](http://127.0.0.1:8001/doc.html)

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

#### nacos配置（2.0.4）

下载地址：[https://github.com/alibaba/nacos/releases](https://github.com/alibaba/nacos/releases)

修改配置文件 `nacos-server-2.0.4/conf/application.properties`

```yml
#*************** Config Module Related Configurations ***************#
### If use MySQL as datasource:
spring.datasource.platform=mysql

### Count of DB:
db.num=1

### Connect URL of DB:
db.url.0=jdbc:mysql://127.0.0.1:3306/call_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
db.user.0=root
db.password.0=root
```

执行 `nacos-server-2.0.4/bin/startup.cmd` 即可启动 Nacos 。

```shell
# linux
cd /usr/local/nacos/bin
./startup.sh -m standalone
```

浏览器访问： [http://localhost:8848/nacos](http://localhost:8848/nacos)

默认用户名密码 `nacos/nacos`

> 如果使用测试环境做注册中心，请创建自己的命名空间注册服务，避免多人混用。

#### sentinel配置（1.8.3）

下载地址：[https://github.com/alibaba/Sentinel/releases](https://github.com/alibaba/Sentinel/releases)

执行命令运行：

```shell
# windows
java -Dserver.port=8858 -Dcsp.sentinel.dashboard.server=localhost:8718 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.3.jar

# linux
nohup  java -Dserver.port=8858 -Dcsp.sentinel.dashboard.server=localhost:8718 -Dproject.name=sentinel-dashboard -jar /usr/local/sentinel/sentinel-dashboard-1.8.3.jar &

```

浏览器访问： [http://localhost:8080](http://localhost:8080)

默认用户名密码 `sentinel/sentinel`

> 其中 -Dserver.port=8080 用于指定 Sentinel 控制台端口为 8080。
>
> 注意：启动 Sentinel 控制台需要 JDK 版本为 1.8 及以上版本。
