package com.tansci.common.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.regex.Pattern;

/**
 * @ClassName： XssHttpServletRequestWrapper.java
 * @ClassPath： com.tansci.common.web.filter.XssHttpServletRequestWrapper.java
 * @Description： sql注入处理
 * @Author： tanyp
 * @Date： 2022/9/1 14:37
 **/
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    HttpServletRequest orgRequest = null;

    XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        orgRequest = request;
    }

    /**
     * @MonthName： getParameter
     * @Description： 覆盖getParameter方法，将参数名和参数值都做xss过滤。
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
     * @Author： tanyp
     * @Date： 2022/9/1 12:56
     * @Param： [name]
     * @return： java.lang.String
     **/
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(xssEncode(name));
        if (value != null) {
            value = xssEncode(value);
            value = HTMLEncode(value);
        }
        return value;
    }

    /**
     * @MonthName： HTMLEncode
     * @Description： 对一些特殊字符进行转义
     * @Author： tanyp
     * @Date： 2022/9/1 12:57
     * @Param： [aText]
     * @return： java.lang.String
     **/
    private static String HTMLEncode(String aText) {
        final StringBuilder result = new StringBuilder();
        final StringCharacterIterator iterator = new StringCharacterIterator(aText);
        char character = iterator.current();
        while (character != CharacterIterator.DONE) {
            if (character == '<') {
                result.append("<");
            } else if (character == '>') {
                result.append(">");
            } else if (character == '&') {
                result.append("&");
            } else if (character == '\"') {
            } else {
                result.append(character);
            }
            character = iterator.next();
        }

        return result.toString();
    }

    /**
     * @MonthName： getHeader
     * @Description： 覆盖getHeader方法，将参数名和参数值都做xss过滤。 如果需要获得原始的值，则通过super.getHeaders(name)来获取
     * getHeaderNames 也可能需要覆盖
     * @Author： tanyp
     * @Date： 2022/9/1 12:57
     * @Param： [name]
     * @return： java.lang.String
     **/
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(xssEncode(name));
        if (value != null) {
            value = xssEncode(value);
        }
        return value;
    }

    /**
     * @MonthName： xssEncode
     * @Description： 将容易引起xss漏洞的半角字符直接替换成全角字符
     * 目前xssProject对注入代码要求是必须开始标签和结束标签(如<script></script>)正确匹配才能解析，否则报错；因此只能替换调xssProject换为自定义实现
     * @Author： tanyp
     * @Date： 2022/9/1 12:59
     * @Param： [s]
     * @return： java.lang.String
     **/
    private static String xssEncode(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        String result = stripXSS(s);
        if (null != result) {
            result = escape(result);
        }
        return result;
    }

    private static String escape(String s) {
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '>':
                    sb.append('＞');// 全角大于号
                    break;
                case '<':
                    sb.append('＜');// 全角小于号
                    break;
                case '\'':
                    sb.append('‘');// 全角单引号
                    break;
                case '\"':
                    sb.append('“');// 全角双引号
                    break;
                case '\\':
                    sb.append('＼');// 全角斜线
                    break;
                case '%':
                    sb.append('％'); // 全角冒号
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    private static String stripXSS(String value) {
        if (value == null) {
            return null;
        }

        String rlt = value;
        Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
        rlt = scriptPattern.matcher(rlt).replaceAll("");

        // Avoid anything in a src='...' type of expression
        scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\'(.*?)\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        rlt = scriptPattern.matcher(rlt).replaceAll("");

        scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        rlt = scriptPattern.matcher(rlt).replaceAll("");

        // Remove any lonesome </script> tag
        scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
        rlt = scriptPattern.matcher(rlt).replaceAll("");

        // Remove any lonesome <script ...> tag
        scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        rlt = scriptPattern.matcher(rlt).replaceAll("");

        // Avoid eval(...) expressions
        scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        rlt = scriptPattern.matcher(rlt).replaceAll("");

        // Avoid expression(...) expressions
        scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        rlt = scriptPattern.matcher(rlt).replaceAll("");

        // Avoid javascript:... expressions
        scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
        rlt = scriptPattern.matcher(rlt).replaceAll("");

        // Avoid vbscript:... expressions
        scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
        rlt = scriptPattern.matcher(rlt).replaceAll("");

        // Avoid onload= expressions
        scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        rlt = scriptPattern.matcher(rlt).replaceAll("");
        return rlt;
    }

    /**
     * @MonthName： getOrgRequest
     * @Description： 获取最原始的request
     * @Author： tanyp
     * @Date： 2022/9/1 12:58
     * @Param： []
     * @return： javax.servlet.http.HttpServletRequest
     **/
    private HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    /**
     * @MonthName： getOrgRequest
     * @Description： 获取最原始的request的静态方法
     * @Author： tanyp
     * @Date： 2022/9/1 12:58
     * @Param： [req]
     * @return： javax.servlet.http.HttpServletRequest
     **/
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
        if (req instanceof XssHttpServletRequestWrapper) {
            return ((XssHttpServletRequestWrapper) req).getOrgRequest();
        }
        return req;
    }

}
