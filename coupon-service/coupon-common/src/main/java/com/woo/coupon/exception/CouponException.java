package com.woo.coupon.exception;

/**
 * @Description 统一异常
 * @Author WooLeo
 * @Date 2020/2/24 2:17 下午
 * @Version 1.0
 */
public class CouponException extends Exception {

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public CouponException(String message) {
        super(message);
    }
}
