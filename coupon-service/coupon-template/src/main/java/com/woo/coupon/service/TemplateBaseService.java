package com.woo.coupon.service;

import com.woo.coupon.entity.CouponTemplate;
import com.woo.coupon.exception.CouponException;
import com.woo.coupon.vo.CouponTemplateSDK;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @title
 * @description 优惠劵  crud
 * @author WooLeo
 * @updateTime 2020/2/29 2:49 下午
 * @throws
 */
public interface TemplateBaseService {

    /**
     * @title
     * @description 根据优惠劵 ID 获取优惠劵模版信息
     * @author WooLeo
     * @updateTime 2020/2/29 2:51 下午
     * @throws
     */
    CouponTemplate buildTemplateInfo (Integer id) throws CouponException;

    /**
     * @title
     * @description 查找所有可用的优惠劵模版
     * @author WooLeo
     * @updateTime 2020/2/29 2:52 下午
     * @throws
     */
    List<CouponTemplateSDK> findAllUsableTemplate();


    /**
     *  获取模版的 ids 到 CouponTemplateSDK 的映射
     * @param ids 模版 ids
     * @return Map<key: id, value: CouponTemplateSDK>
     */
    Map<Integer, CouponTemplateSDK> findIds2TemplateSDK(Collection<Integer> ids);
}
