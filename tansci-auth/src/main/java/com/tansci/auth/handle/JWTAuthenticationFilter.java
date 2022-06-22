package com.tansci.auth.handle;

import com.alibaba.fastjson.JSONObject;
import com.tansci.auth.domain.AuthUser;
import com.tansci.auth.domain.UserVo;
import com.tansci.common.core.constants.JWTConstants;
import com.tansci.common.core.constants.RedisConstants;
import com.tansci.common.core.enums.AuthEnum;
import com.tansci.common.core.utils.JWTUtiles;
import com.tansci.common.core.utils.Md5Utils;
import com.tansci.common.core.utils.WrapMapper;
import com.tansci.common.core.utils.Wrapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @path：com.tansci.auth.handle.JWTAuthenticationFilter.java
 * @className：JWTAuthenticationFilter.java
 * @description：权限变动重新授权
 * @author：tanyp
 * @dateTime：2022/2/11 13:15
 * @editNote：
 */
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private StringRedisTemplate redisTemplate;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, StringRedisTemplate redisTemplate) {
        this.authenticationManager = authenticationManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @SneakyThrows
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        AuthUser user = (AuthUser) authResult.getPrincipal();

        // 生成token
        String payload = JSONObject.toJSONString(user);
        String jwtToken = JWTUtiles.createToken(payload);

        // 生成Key， 把权限放入到redis中
        String keyPrefix = RedisConstants.TOKEN_KEY_PREFIX + user.getId() + "-" + user.getUsername() + ":";
        String keySuffix = Md5Utils.getMD5(jwtToken.getBytes());
        String key = keyPrefix + keySuffix;
        String authKey = key + RedisConstants.AUTH_KEY;

        // 删除旧的未过期的值
        String _key = keyPrefix + "*";
        Set<String> keys = redisTemplate.keys(_key);
        if (Objects.nonNull(keys)) {
            redisTemplate.delete(keys);
        }

        redisTemplate.opsForValue().set(key, jwtToken, JWTConstants.EXPIRE_TIME, TimeUnit.MILLISECONDS);
        redisTemplate.opsForValue().set(authKey, JSONObject.toJSONString(user.getAuthorities()), JWTConstants.EXPIRE_TIME, TimeUnit.SECONDS);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONObject.toJSONString(WrapMapper.success().result(
                UserVo.builder()
                        .username(user.getUsername())
                        .token(jwtToken)
                        .loginTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .build()
        )));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.error("========登录认证失败:", failed);
        Wrapper result = null;
        int status = AuthEnum.AUTH_NO_TOKEN.getKey();
        if (failed instanceof UsernameNotFoundException) {
            result = WrapMapper.error(AuthEnum.AUTH_NONEXISTENT.getKey(), AuthEnum.AUTH_NONEXISTENT.getValue());
        } else if (failed instanceof BadCredentialsException) {
            result = WrapMapper.error(AuthEnum.AUTH_NO_TOKEN.getKey(), AuthEnum.AUTH_NO_TOKEN.getValue());
        }
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(status);
        response.getWriter().write(JSONObject.toJSONString(result));
    }

}
