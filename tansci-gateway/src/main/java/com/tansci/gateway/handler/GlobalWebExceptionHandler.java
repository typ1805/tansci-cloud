package com.tansci.gateway.handler;

import com.alibaba.fastjson.JSON;
import com.tansci.common.core.exception.BusinessException;
import com.tansci.common.core.utils.WrapMapper;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ClassName： GlobalWebExceptionHandler.java
 * @ClassPath： com.tansci.gateway.handler.GlobalWebExceptionHandler.java
 * @Description： Gateway全局异常处理
 * @Author： tanyp
 * @Date： 2022/9/1 14:32
 **/
@Slf4j
@Order(-1)
@Component
public class GlobalWebExceptionHandler implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        // 响应JSON类型数据
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        // 按照异常类型进行翻译处理
        String message;
        if (ex instanceof NotFoundException) {
            response.setStatusCode(HttpStatus.NOT_FOUND);
            message = "您请求的服务不存在！";
        } else if (ex instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) ex;
            response.setStatusCode(responseStatusException.getStatus());
            message = responseStatusException.getMessage();
        } else if (ex instanceof BusinessException) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            message = ex.getMessage();
        } else {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            message = "服务器异常，请稍后再试！";
        }

        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            return bufferFactory.wrap(JSON.toJSONBytes(WrapMapper.error(response.getStatusCode().value(), message)));
        }));
    }

}
