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


## nacos配置（2.0.4）

下载地址：[https://github.com/alibaba/nacos/releases](https://github.com/alibaba/nacos/releases)

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

## sentinel配置（1.8.3）

下载地址：[https://github.com/alibaba/Sentinel/releases](https://github.com/alibaba/Sentinel/releases)

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