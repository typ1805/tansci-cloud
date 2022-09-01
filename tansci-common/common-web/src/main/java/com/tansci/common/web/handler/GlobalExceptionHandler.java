package com.tansci.common.web.handler;

import com.tansci.common.core.exception.BusinessException;
import com.tansci.common.core.utils.WrapMapper;
import com.tansci.common.core.utils.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Objects;

/**
 * @ClassName： GlobalExceptionHandler.java
 * @ClassPath： com.tansci.common.web.handler.GlobalExceptionHandler.java
 * @Description： 全局异常处理流程，根据需要设置需要处理的异常
 * @Author： tanyp
 * @Date： 2022/9/1 14:34
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Wrapper<?> globalException(Exception exception) {
        log.error("业务异常:{},{}", exception.getMessage(), exception);
        return WrapMapper.wrap(Wrapper.ERROR_CODE, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Wrapper<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage(), exception);
        return WrapMapper.wrap(Wrapper.ERROR_CODE, Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public Wrapper<?> handleValidationException(ValidationException exception) {
        log.error(exception.getMessage(), exception);
        return WrapMapper.wrap(Wrapper.ERROR_CODE, exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Wrapper<?> handleConstraintViolationException(ConstraintViolationException exception) {
        log.error(exception.getMessage(), exception);
        return WrapMapper.wrap(Wrapper.ERROR_CODE, exception.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Wrapper<?> handleDuplicateKeyException(DuplicateKeyException exception) {
        log.error(exception.getMessage(), exception);
        return WrapMapper.wrap(Wrapper.ERROR_CODE, "数据重复，请检查后提交");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Wrapper<?> IllegalArgumentException(IllegalArgumentException exception) {
        log.error("参数非法异常:{},{}", exception.getMessage(), exception);
        return WrapMapper.wrap(Wrapper.ERROR_CODE, exception.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public Wrapper<?> businessException(BusinessException exception) {
        log.error("业务异常:{},{}", exception.getMessage(), exception);
        return WrapMapper.wrap(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.OK)
    public Wrapper<?> businessException(MultipartException exception) {
        log.error("业务异常:{},{}", exception.getMessage(), exception);
        return WrapMapper.wrap(Wrapper.ERROR_CODE, exception.getMessage());
    }

}
