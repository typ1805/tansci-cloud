package com.tansci.controller;

import com.alibaba.fastjson.JSON;
import com.nimbusds.jwt.SignedJWT;
import com.tansci.constants.Constants;
import com.tansci.constants.JWTConstants;
import com.tansci.constants.RedisConstants;
import com.tansci.domain.SysUser;
import com.tansci.utils.JWTUtiles;
import com.tansci.utils.WrapMapper;
import com.tansci.utils.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Set;

/**
 * @ClassName： AuthController.java
 * @ClassPath： com.tansci.controller.AuthController.java
 * @Description： 认证中心
 * @Author： tanyp
 * @Date： 2022/2/11 11:43
 **/
@Slf4j
@RestController
public class AuthController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/checkUser")
    public Principal checkUser(Principal user) {
        return user;
    }

    /**
     * @methodName：signout
     * @description：退出
     * @author：tanyp
     * @dateTime：2022/2/11 11:43
     * @Params： [request, response]
     * @Return： com.mall.utils.Wrapper<java.lang.Object>
     * @editNote：
     */
    @PostMapping("signout")
    public Wrapper<Object> signout(HttpServletRequest request, HttpServletResponse response) {
        String headerToken = request.getHeader(JWTConstants.TOKEN_HEADER);
        try {
            // 清除redis缓存
            SignedJWT jwt = JWTUtiles.getSignedJWT(headerToken);
            SysUser user = JSON.parseObject(jwt.getJWTClaimsSet().getClaim("payload").toString(), SysUser.class);
            String keyPrefix = RedisConstants.TOKEN_KEY_PREFIX + user.getId() + "-" + user.getUsername() + ":*";
            Set keys = redisTemplate.keys(keyPrefix);
            log.info("=========清除redis缓存 keys:{}", JSON.toJSON(keys));
            redisTemplate.delete(keys);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, Constants.LOGOUT_MESSAGE);
        } catch (Exception e) {
            log.error("=========退出异常：{}", e.getMessage());
            e.printStackTrace();
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE, Constants.ERROR_MESSAGE);
        }
    }

}
