package com.tansci.utils;

import java.util.Objects;

/**
 * @ClassName： WrapMapper.java
 * @ClassPath： com.tansci.utils.WrapMapper.java
 * @Description： 返回包装类
 * @Author： tanyp
 * @Date： 2022/2/11 9:53
 **/
public class WrapMapper {

    private WrapMapper() {
    }

    /**
     * Wrap.
     *
     * @param <E>     the element type
     * @param code    the code
     * @param message the message
     * @param o       the o
     * @return the wrapper
     */
    public static <E> Wrapper<E> wrap(int code, String message, E o) {
        return new Wrapper<E>(code, message, o);
    }

    /**
     * Wrap.
     *
     * @param <E>     the element type
     * @param code    the code
     * @param message the message
     * @return the wrapper
     */
    public static <E> Wrapper<E> wrap(int code, String message) {
        return new Wrapper<E>(code, message);
    }

    /**
     * Wrap.
     *
     * @param <E>  the element type
     * @param code the code
     * @return the wrapper< e>
     */
    public static <E> Wrapper<E> wrap(int code) {
        return wrap(code, null);
    }

    /**
     * Wrap.
     *
     * @param <E> the element type
     * @param e   the e
     * @return the wrapper
     */
    public static <E> Wrapper<E> wrap(Exception e) {
        return new Wrapper<E>(Wrapper.ERROR_CODE, e.getMessage());
    }

    /**
     * Un wrapper.
     *
     * @param <E>     the element type
     * @param wrapper the wrapper
     * @return the e
     */
    public static <E> E unWrap(Wrapper<E> wrapper) {
        return wrapper.getResult();
    }

    /**
     * Wrap ERROR. code=100
     *
     * @param <E> the element type
     * @return the wrapper< e>
     */
    public static <E> Wrapper<E> illegalArgument() {
        return wrap(Wrapper.ILLEGAL_ARGUMENT_CODE_, Wrapper.ILLEGAL_ARGUMENT_MESSAGE);
    }

    /**
     * Wrap ERROR. code=500
     *
     * @param <E> the element type
     * @return the wrapper< e>
     */
    public static <E> Wrapper<E> error() {
        return wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
    }

    /**
     * Error wrapper.
     *
     * @param <E>     the type parameter
     * @param message the message
     * @return the wrapper
     */
    public static <E> Wrapper<E> error(String message) {
        return wrap(Wrapper.ERROR_CODE, Objects.isNull(message) ? Wrapper.ERROR_MESSAGE : message);
    }

    public static <E> Wrapper<E> error(int code, String message) {
        return wrap(code, Objects.isNull(message) ? Wrapper.ERROR_MESSAGE : message);
    }

    /**
     * Wrap SUCCESS. code=200
     *
     * @param <E> the element type
     * @return the wrapper< e>
     */
    public static <E> Wrapper<E> ok() {
        return new Wrapper<E>();
    }

    /**
     * Ok wrapper.
     *
     * @param <E> the type parameter
     * @param o   the o
     * @return the wrapper
     */
    public static <E> Wrapper<E> wrap(E o) {
        return new Wrapper<>(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, o);
    }

    public static <E> Wrapper<E> ok(E o) {
        return new Wrapper<>(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, o);
    }

    public static <E> Wrapper<E> success() {
        return new Wrapper<>(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE);
    }

}
