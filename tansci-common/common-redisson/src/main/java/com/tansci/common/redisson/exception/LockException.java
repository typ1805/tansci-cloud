package com.tansci.common.redisson.exception;

/**
 * @ClassName： LockException.java
 * @ClassPath： com.tansci.common.redisson.exception.LockException.java
 * @Description： 分布式锁异常处理
 * @Author： tanyp
 * @Date： 2022/8/31 9:08
 **/
public class LockException extends RuntimeException {

    /**
     * 异常码
     */
    private int code = 500;

    /**
     * 异常描述
     */
    private String message;

    public LockException(Integer code, String message, Throwable e) {
        super(message, e);
        this.code = code;
        this.message = message;
    }

    public LockException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public LockException(String message) {
        super(message);
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return code + ": " + message;
    }

}
