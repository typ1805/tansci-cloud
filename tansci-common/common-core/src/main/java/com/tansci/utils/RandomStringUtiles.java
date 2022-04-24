package com.tansci.utils;

import java.util.Locale;
import java.util.UUID;

/**
 * @ClassName： RandomStringUtiles.java
 * @ClassPath： com.tansci.utils.RandomStringUtiles.java
 * @Description： 随机字符串
 * @Author： tanyp
 * @Date： 2022/4/15 14:34
 **/
public class RandomStringUtiles {

    /**
     * @MonthName： random8Bits
     * @Description： 生成8位随机字符串（重复概率1/218万亿）
     * @Author： tanyp
     * @Date： 2022/4/15 15:14
     * @Param： [s]
     * @return： java.lang.String
     **/
    public static String random8Bits(String prefix) {
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
                "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
                "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z"};

        StringBuffer shortBuffer = new StringBuffer();
        shortBuffer.append(prefix);
        String uuid = UUID.randomUUID().toString().replace("-", "");

        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString().toLowerCase(Locale.ROOT);
    }


}
