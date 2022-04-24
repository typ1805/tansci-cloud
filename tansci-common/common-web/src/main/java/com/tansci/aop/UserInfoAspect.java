package com.tansci.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tansci.base.UserInfo;
import com.tansci.constants.JWTConstants;
import com.tansci.domain.SysUser;
import com.tansci.utils.JWTUtiles;
import com.tansci.utils.UserInfoContext;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.Objects;

/**
 * @ClassName： UserInfoAspect.java
 * @ClassPath： com.tansci.aop.UserInfoAspect.java
 * @Description： 用户信息共享
 * @Author： tanyp
 * @Date： 2022/2/15 8:51
 **/
@Slf4j
@Order(1)
@Aspect
@Component
public class UserInfoAspect {

    @Pointcut("execution(* com.tansci.controller..*.*(..))")
    public void userInfoPointcut() {
    }

    /**
     * @MonthName： around
     * @Description： 用户信息共享
     * @Author： tanyp
     * @Date： 2022/2/22 17:49
     * @Param： [point]
     * @return： java.lang.Object
     **/
    @Around("userInfoPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        try {
            SysUser user = getUser(request);
            if (Objects.nonNull(user)) {
                UserInfoContext.setUser(UserInfo.builder().id(user.getId()).username(user.getUsername()).type(user.getType()).build());
            }
            return point.proceed();
        } catch (Exception e) {
            log.error("==============共享用户信息异常=========={}=========", e.getMessage());
        }
        return point.proceed();
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
