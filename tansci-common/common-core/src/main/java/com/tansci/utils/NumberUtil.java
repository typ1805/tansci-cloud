package com.tansci.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName： NumberUtil.java
 * @ClassPath： com.tansci.utils.NumberUtil.java
 * @Description： 数值工具类
 * @Author： tanyp
 * @Date： 2022/2/11 9:58
 **/
public class NumberUtil {

    /**
     * @methodName：isPhone
     * @description：判断是否为11位电话号码
     * @author：tanyp
     * @dateTime：2022/2/11 9:58
     * @Params： [phone]
     * @Return： boolean
     * @editNote：
     */
    public static boolean isPhone(String phone) {
        Pattern pattern = Pattern.compile("^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[0-8])|(18[0-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    /**
     * @methodName：genRandomNum
     * @description：生成指定长度的随机数
     * @author：tanyp
     * @dateTime：2022/2/11 9:58
     * @Params： [length]
     * @Return： int
     * @editNote：
     */
    public static int genRandomNum(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

}
