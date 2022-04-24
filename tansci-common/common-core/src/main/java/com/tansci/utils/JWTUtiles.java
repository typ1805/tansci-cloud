package com.tansci.utils;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.tansci.constants.JWTConstants;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.util.Date;

/**
 * @ClassName： JWTUtiles.java
 * @ClassPath： com.tansci.utils.JWTUtiles.java
 * @Description： JWT工具类
 * @Author： tanyp
 * @Date： 2022/2/11 9:56
 **/
@Slf4j
public class JWTUtiles {

    /**
     * @methodName：createToken
     * @description：创建token
     * @author：tanyp
     * @dateTime：2022/2/11 10:29
     * @Params： [user]
     * @Return： java.lang.String
     * @editNote：
     */
    public static String createToken(String payload) throws JOSEException {
        // 创建密钥
        MACSigner macSigner = new MACSigner(JWTConstants.SECRET);

        // payload
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject("subject")
                .claim("payload", payload)
                .expirationTime(new Date(System.currentTimeMillis() + JWTConstants.EXPIRE_TIME))
                .build();
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        // 创建签名的JWT
        SignedJWT signedJWT = new SignedJWT(jwsHeader, claimsSet);
        signedJWT.sign(macSigner);

        // 生成token
        String jwtToken = signedJWT.serialize();
        return jwtToken;
    }

    /**
     * @methodName：verifierToken
     * @description：验证token
     * @author：tanyp
     * @dateTime：2022/2/11 10:29
     * @Params： [headerToken]
     * @Return： boolean
     * @editNote：
     */
    public static boolean verifierToken(String headerToken) {
        try {
            SignedJWT jwt = getSignedJWT(headerToken);
            JWSVerifier verifier = new MACVerifier(JWTConstants.SECRET);
            // 校验是否有效
            if (!jwt.verify(verifier)) {
                log.error("token不合法，检测不过关");
                return false;
            }

            // 校验超时
            Date expirationTime = jwt.getJWTClaimsSet().getExpirationTime();
            if (new Date().after(expirationTime)) {
                log.error("token已经过期");
                return false;
            }
            // 获取载体中的数据
            return true;
        } catch (ParseException | JOSEException e) {
            log.error("token校验出错", e);
        }
        return false;
    }

    public static SignedJWT getSignedJWT(String headerToken) throws ParseException {
        String token = headerToken.replace(JWTConstants.TOKEN_PREFIX, "");
        log.info("token is {}", token);
        return SignedJWT.parse(token);
    }

}
