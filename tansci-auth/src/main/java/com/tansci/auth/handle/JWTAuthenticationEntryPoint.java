package com.tansci.auth.handle;

import com.alibaba.fastjson.JSONObject;
import com.tansci.common.core.utils.WrapMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @path：com.tansci.auth.handle.JWTAuthenticationEntryPoint.java
 * @className：JWTAuthenticationEntryPoint.java
 * @description：403打印
 * @author：tanyp
 * @dateTime：2022/2/11 13:11
 * @editNote：
 */
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        String reason = authException.getMessage();
        response.getWriter().write(JSONObject.toJSONString(WrapMapper.error(HttpServletResponse.SC_FORBIDDEN, reason)));
    }

}
