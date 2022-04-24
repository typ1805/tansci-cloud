package com.tansci.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName： DataUtils.java
 * @ClassPath： com.tansci.utils.DataUtils.java
 * @Description： 数据处理
 * @Author： tanyp
 * @Date： 2022/2/28 9:21
 **/
@Slf4j
public class DataUtils {

    private static final Pattern pattern = Pattern.compile("\\#\\{(.*?)\\}");
    private static Matcher matcher;

    /**
     * @MonthName： strRreplace
     * @Description： 字符串站位处理
     * @Author： tanyp
     * @Date： 2022/2/28 9:21
     * @Param： [content, param]
     * @return： java.lang.String
     **/
    public static String strRreplace(String content, Map<String, Object> param) {
        if (Objects.isNull(param)) {
            return null;
        }
        try {
            matcher = pattern.matcher(content);
            while (matcher.find()) {
                String key = matcher.group();
                String keyclone = key.substring(2, key.length() - 1).trim();
                boolean containsKey = param.containsKey(keyclone);
                if (containsKey && Objects.nonNull(param.get(keyclone))) {
                    String value = "'" + param.get(keyclone) + "'";
                    content = content.replace(key, value);
                }
            }
            return content;
        } catch (Exception e) {
            log.error("字符串站位处理：{}", e);
            return null;
        }
    }

}