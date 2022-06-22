package com.tansci.common.web.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nimbusds.jwt.SignedJWT;
import com.tansci.admin.api.domain.LogErrorInfo;
import com.tansci.admin.api.domain.LogInfo;
import com.tansci.admin.api.domain.SysUser;
import com.tansci.admin.api.provider.LogProvider;
import com.tansci.common.core.constants.JWTConstants;
import com.tansci.common.core.utils.JWTUtiles;
import com.tansci.common.web.annotation.Log;
import com.tansci.common.web.utils.SystemUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @ClassName： LogAspect.java
 * @ClassPath： com.tansci.common.web.aop.LogAspect.java
 * @Description： 日志处理切面
 * @Author： tanyp
 * @Date： 2022/2/15 8:51
 **/
@Slf4j
@Order(2)
@Aspect
@Component
public class LogAspect {

    @DubboReference
    private LogProvider logProvider;

    /**
     * 操作版本号
     * 项目启动时从命令行传入，例如：java -jar xxx.war --version=201902
     */
//    @Value("${version}")
    private String version = "1.0.0";

    /**
     * 统计请求的处理时间
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * @methodName：logPoinCut
     * @description：设置操作日志切入点 记录操作日志 在注解的位置切入代码
     * @author：tanyp
     * @dateTime：2022/2/15 14:19
     * @Params： []
     * @Return： void
     * @editNote：
     */
    @Pointcut("@annotation(com.tansci.common.web.annotation.Log)")
    public void logPoinCut() {
    }

    /**
     * @methodName：exceptionLogPoinCut
     * @description：设置操作异常切入点记录异常日志 扫描所有controller包下操作
     * @author：tanyp
     * @dateTime：2022/2/15 14:19
     * @Params： []
     * @Return： void
     * @editNote：
     */
//    @Pointcut("execution(* com.tansci..*.controller.*.*(..))")
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) || within(@org.springframework.stereotype.Controller *)")
    public void exceptionLogPoinCut() {
    }

    @Before("logPoinCut()")
    public void doBefore() {
        // 接收到请求，记录请求开始时间
        startTime.set(System.currentTimeMillis());
    }

    /**
     * @methodName：doAfterReturning
     * @description：正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
     * @author：tanyp
     * @dateTime：2022/2/15 14:19
     * @Params： [joinPoint, keys]
     * @Return： void
     * @editNote：
     */
    @AfterReturning(value = "logPoinCut()", returning = "keys")
    public void doAfterReturning(JoinPoint joinPoint, Object keys) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        LogInfo logInfo = LogInfo.builder().build();
        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();

            // 获取切入点所在的方法
            Method method = signature.getMethod();

            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();

            // 获取操作
            Log log = method.getAnnotation(Log.class);
            if (Objects.nonNull(log)) {
                logInfo.setModule(log.modul());
                logInfo.setType(log.type());
                logInfo.setMessage(log.desc());
            }

            SysUser user = getUser(request);
            if (Objects.nonNull(user)) {
                logInfo.setUserId(user.getId()); // 请求用户ID
                logInfo.setUserName(user.getUsername()); // 请求用户名称
            }

            logInfo.setId(UUID.randomUUID().toString());
            logInfo.setMethod(className + "." + method.getName()); // 请求的方法名
            logInfo.setReqParam(JSON.toJSONString(converMap(request.getParameterMap()))); // 请求参数
            logInfo.setResParam(JSON.toJSONString(keys)); // 返回结果
            logInfo.setIp(SystemUtils.getIpAddress(request)); // 请求IP
            logInfo.setUri(request.getRequestURI()); // 请求URI
            logInfo.setCreateTime(LocalDateTime.now()); // 创建时间
            logInfo.setVersion(version); // 操作版本
            logInfo.setTakeUpTime(System.currentTimeMillis() - startTime.get()); // 耗时
            logProvider.logInfoSave(logInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @methodName：doAfterThrowing
     * @description：异常返回通知，用于拦截异常日志信息 连接点抛出异常后执行
     * @author：tanyp
     * @dateTime：2022/2/15 14:19
     * @Params： [joinPoint, e]
     * @Return： void
     * @editNote：
     */
    @AfterThrowing(pointcut = "exceptionLogPoinCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();

            // 获取切入点所在的方法
            Method method = signature.getMethod();

            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();

            LogErrorInfo logErrorInfo = LogErrorInfo.builder()
                    .id(UUID.randomUUID().toString())
                    .reqParam(JSON.toJSONString(converMap(request.getParameterMap()))) // 请求参数
                    .method(className + "." + method.getName()) // 请求方法名
                    .name(e.getClass().getName()) // 异常名称
                    .message(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace())) // 异常信息
                    .uri(request.getRequestURI()) // 操作URI
                    .ip(SystemUtils.getIpAddress(request)) // 操作员IP
                    .version(version) // 版本号
                    .createTime(LocalDateTime.now()) // 发生异常时间
                    .build();

            SysUser user = getUser(request);
            if (Objects.nonNull(user)) {
                logErrorInfo.setUserId(user.getId()); // 请求用户ID
                logErrorInfo.setUserName(user.getUsername()); // 请求用户名称
            }

            logProvider.logErrorInfoSave(logErrorInfo);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /**
     * @methodName：converMap
     * @description：转换request 请求参数
     * @author：tanyp
     * @dateTime：2022/2/15 14:19
     * @Params： [paramMap]
     * @Return： java.util.Map<java.lang.String, java.lang.String>
     * @editNote：
     */
    public Map<String, String> converMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<String, String>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }

    /**
     * @methodName：stackTraceToString
     * @description：转换异常信息为字符串
     * @author：tanyp
     * @dateTime：2022/2/15 14:19
     * @Params： [exceptionName, exceptionMessage, elements]
     * @Return： java.lang.String
     * @editNote：
     */
    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
        for (StackTraceElement stet : elements) {
            strbuff.append(stet + "<br/>");
        }
        String message = exceptionName + ":" + exceptionMessage + "<br/>" + strbuff.toString();
        return message;
    }

    /**
     * @MonthName： getUser
     * @Description： 获取用户信息
     * @Author： tanyp
     * @Date： 2022/2/15 13:23
     * @Param： [request]
     * @return： com.kuiper.domain.SysUser
     **/
    public SysUser getUser(HttpServletRequest request) {
        SysUser user = SysUser.builder().build();
        // 获取请求头信息,需要注意的是，请求头中的key都会转成小写
        Enumeration<String> enumeration = request.getHeaderNames();
        JSONObject headers = new JSONObject();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            headers.put(name, value);
        }

        Object token = headers.get(JWTConstants.TOKEN_HEADER.toLowerCase());
        if (Objects.nonNull(token)) {
            try {
                SignedJWT jwt = JWTUtiles.getSignedJWT(String.valueOf(token));
                Object payload = jwt.getJWTClaimsSet().getClaim("payload");
                user = JSON.parseObject(payload.toString(), SysUser.class);
            } catch (ParseException e) {
                log.error("====日志记录获取用户信息异常====：{}", e);
            }
        }
        return user;
    }

}
