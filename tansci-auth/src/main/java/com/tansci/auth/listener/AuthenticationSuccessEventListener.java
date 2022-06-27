package com.tansci.auth.listener;

import com.tansci.admin.api.domain.LoginLog;
import com.tansci.admin.api.provider.LogProvider;
import com.tansci.auth.domain.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @ClassName： AuthenticationSuccessEventListener.java
 * @ClassPath： com.tansci.auth.listener.AuthenticationSuccessEventListener.java
 * @Description： 认证成功监听
 * @Author： tanyp
 * @Date： 2022/6/27 11:34
 **/
@Slf4j
@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @DubboReference
    private LogProvider logProvider;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        if (event.getSource().getClass().getName().equals("org.springframework.security.authentication.UsernamePasswordAuthenticationToken")) {
            try {
                // 获取 HttpServletRequest
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

                // 获取用户信息
                AuthUser user = (AuthUser) event.getAuthentication().getPrincipal();
                logProvider.loginLogSave(
                        LoginLog.builder()
                                .userId(user.getId())
                                .userName(user.getUsername())
                                .ip(getIpAddress(request))
                                .os(getOS(request))
                                .browser(getBrowser(request))
                                .loginTime(LocalDateTime.now())
                                .createTime(LocalDateTime.now())
                                .build()
                );
            } catch (Exception e) {
                log.error("=========登录成功保存登录日志异常：{}", e.getMessage());
            }
        }
    }

    /**
     * @MonthName： getOS
     * @Description： 获取操作系统名称
     * @Author： tanyp
     * @Date： 2022/6/1 15:30
     * @Param： [request]
     * @return： java.lang.String
     **/
    public static String getOS(HttpServletRequest request) {
        String osName = "";
        String userAgent = request.getHeader("User-Agent").toUpperCase();
        if (userAgent.contains("WINDOWS")) {
            if (userAgent.contains("WINDOWS NT 10.0")) {
                osName = "Windows 10";
            } else if (userAgent.contains("WINDOWS NT 6.2")) {
                osName = "Windows 8";
            } else if (userAgent.contains("WINDOWS NT 6.1")) {
                osName = "Windows 7";
            } else if (userAgent.contains("WINDOWS NT 6.0")) {
                osName = "Windows Vista";
            } else if (userAgent.contains("WINDOWS NT 5.2")) {
                osName = "Windows XP";
            } else if (userAgent.contains("WINDOWS NT 5.1")) {
                osName = "Windows XP";
            } else if (userAgent.contains("WINDOWS NT 5.01")) {
                osName = "Windows 2000";
            } else if (userAgent.contains("WINDOWS NT 5.0")) {
                osName = "Windows 2000";
            } else if (userAgent.contains("WINDOWS NT 4.0")) {
                osName = "Windows NT 4.0";
            } else if (userAgent.contains("WINDOWS 98; WIN 9X 4.90")) {
                osName = "Windows ME";
            } else if (userAgent.contains("WINDOWS 98")) {
                osName = "Windows 98";
            } else if (userAgent.contains("WINDOWS 95")) {
                osName = "Windows 95";
            } else if (userAgent.contains("WINDOWS CE")) {
                osName = "Windows CE";
            }
        } else if (userAgent.contains("MAC")) {
            osName = "Mac";
        } else if (userAgent.contains("UNIX")) {
            osName = "UNIX";
        } else if (userAgent.contains("LINUX")) {
            osName = "Linux";
        } else if (userAgent.contains("SUNOS")) {
            osName = "SunOS";
        }
        return osName;
    }

    /**
     * @MonthName： getBrowser
     * @Description： 获取浏览器名称
     * @Author： tanyp
     * @Date： 2022/6/1 15:29
     * @Param： [request]
     * @return： java.lang.String
     **/
    public static String getBrowser(HttpServletRequest request) {
        String browserName = "";
        String userAgent = request.getHeader("User-Agent").toUpperCase();
        if (userAgent == null || userAgent.equals("")) {
            return "";
        }
        if (userAgent.indexOf("MSIE") > 0) {
            browserName = "IE";
        } else if (userAgent.indexOf("FIREFOX") > 0) {
            browserName = "Firefox";
        } else if (userAgent.indexOf("CHROME") > 0) {
            browserName = "Chrome";
        } else if (userAgent.indexOf("SAFARI") > 0) {
            browserName = "Safari";
        } else if (userAgent.indexOf("CAMINO") > 0) {
            browserName = "Camino";
        } else if (userAgent.indexOf("KONQUEROR") > 0) {
            browserName = "Konqueror";
        } else if (userAgent.indexOf("EDGE") > 0) {
            browserName = "Microsoft Edge";
        }
        return browserName;
    }

    /**
     * @methodName：getIpAddress
     * @description：获取IP地址
     * @author：tanyp
     * @dateTime：2022/2/15 14:19
     * @Params： [request]
     * @Return： java.lang.String
     * @editNote：
     */
    public static String getIpAddress(HttpServletRequest request) {

        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_VIA");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(",")) {
            return ip.split(",")[0];
        } else {
            return ip;
        }
    }

}
