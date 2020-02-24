package com.woo.coupon.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class AccessLogFilter extends AbstractPostFilter {
    @Override
    protected Object cRun() {
        HttpServletRequest request = requestContext.getRequest();
        // 从 PreRequestFilter 中获取请求时间戳
        Long startTime = (Long)requestContext.get("startTime");
        Long duration = System.currentTimeMillis() - startTime;

        String uri = request.getRequestURI();

        // 从网关通过的请求都会打印日志记录 uri + duration
        log.info("uri: {}, duration: {}", uri, duration);

        return success();
    }

    /**
     * filterOrder() must also be defined for a filter. Filters may have the same  filterOrder if precedence is not
     * important for a filter. filterOrders do not need to be sequential.
     *
     * @return the int order of a filter
     */
    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
    }
}
