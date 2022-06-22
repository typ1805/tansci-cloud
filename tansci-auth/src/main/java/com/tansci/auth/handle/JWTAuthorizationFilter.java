package com.tansci.auth.handle;

import com.alibaba.fastjson.JSONObject;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import com.tansci.auth.domain.AuthUser;
import com.tansci.common.core.constants.JWTConstants;
import com.tansci.common.core.utils.WrapMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * @path：com.tansci.auth.handle.JWTAuthorizationFilter.java
 * @className：JWTAuthorizationFilter.java
 * @description：授权
 * @author：tanyp
 * @dateTime：2022/3/11 13:19
 * @editNote：
 */
@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(JWTConstants.TOKEN_HEADER);
        if (StringUtils.isEmpty(token) || !token.startsWith(JWTConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        try {
            Authentication authentication = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            onSuccessfulAuthentication(request, response, authentication);
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            onUnsuccessfulAuthentication(request, response, new AccountExpiredException(e.getMessage()));
        }
    }

    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
        log.info("=============Token 验证成功=================");
    }

    @Override
    protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        log.error("================token校验失败=======================");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(JSONObject.toJSONString(WrapMapper.error(HttpServletResponse.SC_UNAUTHORIZED, failed.getMessage())));
    }

    /**
     * @methodName：getAuthentication
     * @description：这里从token中获取用户信息并新建一个token
     * @author：tanyp
     * @dateTime：2022/2/11 13:37
     * @Params： [tokenHeader]
     * @Return： org.springframework.security.authentication.UsernamePasswordAuthenticationToken
     * @editNote：
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) throws ParseException, JOSEException {
        String token = tokenHeader.replace(JWTConstants.TOKEN_PREFIX, "");
        SignedJWT jwt = SignedJWT.parse(token);
        JWSVerifier verifier = new MACVerifier(JWTConstants.SECRET);

        // 校验是否有效
        if (!jwt.verify(verifier)) {
            throw new AccountExpiredException(JWTConstants.TOKEN_INVALID);
        }

        // 校验超时
        Date expirationTime = jwt.getJWTClaimsSet().getExpirationTime();
        if (new Date().after(expirationTime)) {
            throw new AccountExpiredException(JWTConstants.TOKEN_EXPIRE);
        }

        // 获取载体中的数据
        Object account = jwt.getJWTClaimsSet().getClaim("payload");
        if (account != null) {
            AuthUser user = JSONObject.parseObject(account.toString(), AuthUser.class);
            return new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
        }
        return null;
    }

}
