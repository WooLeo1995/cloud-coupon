package com.woo.coupon.advice;

import com.woo.coupon.annotation.IgnoreResponseAdvice;
import com.woo.coupon.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 公共响应对象封装增强
 * 拦截系统中所有controller的返回， 对返回做处理
 */
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {

    /**
     * Whether this component supports the given controller method return type
     * and the selected {@code HttpMessageConverter} type.
     *
     * @param returnType    the return type
     * @param converterType the selected converter type
     * @return {@code true} if {@link #beforeBodyWrite} should be invoked;
     * {@code false} otherwise
     */
    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        // 1. 判断当前方法所在的类标识了 @IgnoreResponseAdvice 注解， 不需要处理
        if(returnType.getDeclaringClass().isAnnotationPresent(
                IgnoreResponseAdvice.class)){
            return false;
        }
        // 2. 判断当前方法标识了 @IgnoreResponseAdvice 注解， 不需要处理
        if(returnType.getMethod().isAnnotationPresent(
                IgnoreResponseAdvice.class
        )){
            return false;
        }
        // 3.需要对响应进行处理， 执行 beforeBodyWrite 方法
        return true;
    }

    /**
     * Invoked after an {@code HttpMessageConverter} is selected and just before
     * its write method is invoked.
     *
     * @param body                  the body to be written
     * @param returnType            the return type of the controller method
     * @param selectedContentType   the content type selected through content negotiation
     * @param selectedConverterType the converter type selected to write to the response
     * @param request               the current request
     * @param response              the current response
     * @return the body that was passed in or a modified (possibly new) instance
     */
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        // 定义最终的返回对象
        CommonResponse<Object> commonResponse = new CommonResponse<>(0, "");

        // 如果 body 为空， 直接返回
        if(null == body){
            return commonResponse;
        } else if(body instanceof CommonResponse){
            commonResponse = (CommonResponse<Object>)body;
            return commonResponse;
        } else {
            commonResponse.setData(body);
            return commonResponse;
        }
    }
}
