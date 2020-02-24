package com.woo.coupon.advice;

import com.woo.coupon.exception.CouponException;
import com.woo.coupon.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 全局异常处理
 * @Author WooLeo
 * @Date 2020/2/24 2:19 下午
 * @Version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {
    /**
     * @title handlerCouponException
     * @description 对 CouponException 进行统一异常处理
     * @author WooLeo
     * @updateTime 2020/2/24 2:22 下午 
     * @throws 
     */
    @ExceptionHandler(value = CouponException.class)
    public CommonResponse<String> handlerCouponException(HttpServletRequest request,
                                                         CouponException exception){
        CommonResponse<String> response = new CommonResponse<>(-1, "business error");
        response.setData(exception.getMessage());
        return response;
    }
}
