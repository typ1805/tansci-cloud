package com.tansci.auth.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName： AuthenticationFailureEventListener.java
 * @ClassPath： com.tansci.auth.listener.AuthenticationFailureEventListener.java
 * @Description： 认证失败监听
 * @Author： tanyp
 * @Date： 2022/6/27 11:31
 **/
@Slf4j
@Component
public class AuthenticationFailureEventListener implements ApplicationListener<AbstractAuthenticationFailureEvent> {

    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
        // 获取 HttpServletRequest
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        log.info("======登录失败:{}", event.getAuthentication().getPrincipal());
    }

}
