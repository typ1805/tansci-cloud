package com.tansci.common.sentinel.parser;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName： HeaderRequestOriginParser.java
 * @ClassPath： com.tansci.common.sentinel.parser.HeaderRequestOriginParser.java
 * @Description： sentinel 请求头解析判断
 * @Author： tanyp
 * @Date： 2022/9/23 14:09
 **/
public class HeaderRequestOriginParser implements RequestOriginParser {

    /**
     * 请求头获取allow
     */
    private static final String ALLOW = "Allow";

    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader(ALLOW);
    }

}
