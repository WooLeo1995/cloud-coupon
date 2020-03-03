package com.woo.coupon.service;

import com.woo.coupon.entity.CouponTemplate;
import com.woo.coupon.exception.CouponException;
import com.woo.coupon.vo.TemplateResquest;

public interface BuildTemplateService {

    /**
     * @title
     * @description
     * @author WooLeo
     * @updateTime 2020/2/29 10:03 上午
     * @throws
     */
    CouponTemplate buildTemplate(TemplateResquest templateResquest) throws CouponException;
}
