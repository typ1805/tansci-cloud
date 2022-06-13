package com.tansci.utils;

import java.security.MessageDigest;

/**
 * @path：com.tansci.utils.MD5Util.java
 * @className：MD5Util.java
 * @description：MD5工具类
 * @author：tanyp
 * @dateTime：2022/06/13 11:33
 * @editNote：
 */
public class MD5Util {

    private static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * MD5加密
     */
    public static String encrypt(String value) {
        return encrypt(value.getBytes());
    }

    /**
     * MD5加密
     */
    public static String encrypt(byte[] value) {
        try {
            byte[] bytes = MessageDigest.getInstance("MD5").digest(value);
            char[] chars = new char[32];
            for (int i = 0; i < chars.length; i = i + 2) {
                byte b = bytes[i / 2];
                chars[i] = HEX_CHARS[(b >>> 0x4) & 0xf];
                chars[i + 1] = HEX_CHARS[b & 0xf];
            }
            return new String(chars);
        } catch (Exception e) {
            throw new RuntimeException("md5 encrypt error", e);
        }
    }

}
