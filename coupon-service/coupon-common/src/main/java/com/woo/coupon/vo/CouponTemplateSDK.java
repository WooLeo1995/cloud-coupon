package com.woo.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 微服务之间用的优惠劵模版信息定义
 * @Author WooLeo
 * @Date 2020/2/29 2:44 下午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponTemplateSDK {

    private Integer id;

    private String name;

    private String logo;

    // 描述
    private String desc;

    private String category;

    private Integer productLine;

    // 优惠劵模版编码
    private String key;

    private TemplateRule rule;

    // 目标用户
    private Integer target;
}
