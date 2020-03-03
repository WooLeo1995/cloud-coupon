package com.woo.coupon.dao;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.woo.coupon.entity.CouponTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description 优惠劵模版 接口定义
 * @Author WooLeo
 * @Date 2020/2/26 10:43 上午
 * @Version 1.0
 */
public interface CouponTemplateDao extends JpaRepository<CouponTemplate, Integer> {

    /**
     * @title
     * @description 根据名称查找
     * @author WooLeo
     * @updateTime 2020/2/29 8:57 上午
     * @throws
     */
    CouponTemplate findAllByName(String name);

    /**
     * @title
     * @description 根据 Available 和 Expired 查找
     * @author WooLeo
     * @updateTime 2020/2/29 9:00 上午
     * @throws
     */
    List<CouponTemplate> findAllByAvailableAndExpired(Boolean available, Boolean expired);
}
