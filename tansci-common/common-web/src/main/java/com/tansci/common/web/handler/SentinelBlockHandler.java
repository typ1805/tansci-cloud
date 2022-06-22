package com.tansci.common.web.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName： SentinelBlockHandler.java
 * @ClassPath： com.tansci.common.web.handler.SentinelBlockHandler.java
 * @Description： 服务限流、熔断处理
 * @Author： tanyp
 * @Date： 2022/2/14 15:38
 **/
@Slf4j
@Component
public class SentinelBlockHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        Map<String, Object> info = new HashMap<>();
        if (e instanceof FlowException) {
            info.put("code", -1);
            info.put("message", "限流异常");
            log.info("=============限流异常===============");
        } else if (e instanceof DegradeException) {
            info.put("code", -2);
            info.put("message", "降级异常");
            log.info("=============降级异常===============");
        } else if (e instanceof ParamFlowException) {
            info.put("code", -3);
            info.put("message", "热点参数异常");
            log.info("=============热点参数异常===============");
        } else if (e instanceof SystemBlockException) {
            info.put("code", -4);
            info.put("message", "系统负载异常");
            log.info("=============系统负载异常===============");
        } else if (e instanceof AuthorityException) {
            info.put("code", -5);
            info.put("message", "授权异常");
            log.info("=============授权异常===============");
        }

        httpServletResponse.setStatus(200);
        httpServletResponse.setHeader("content-type", "application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(info));
    }

}
