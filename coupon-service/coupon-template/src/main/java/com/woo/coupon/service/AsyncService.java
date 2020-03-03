package com.woo.coupon.service;

import com.woo.coupon.entity.CouponTemplate;

public interface AsyncService {

    /**
     * @title
     * @description 依据模版异步创建优惠劵码
     * @param template {@link CouponTemplate} 优惠劵码实体
     * @author WooLeo
     * @updateTime 2020/2/29 2:40 下午
     * @throws
     */
    void asyncConstructCouponByTemplate(CouponTemplate template);
}
