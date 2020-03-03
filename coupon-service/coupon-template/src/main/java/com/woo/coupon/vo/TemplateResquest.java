package com.woo.coupon.vo;

import com.woo.coupon.constant.CouponCategory;
import com.woo.coupon.constant.DistributeTarget;
import com.woo.coupon.constant.ProductLine;
import com.woo.coupon.converter.CouponCategoryConverter;
import com.woo.coupon.converter.DistributeTargetConverter;
import com.woo.coupon.converter.ProductLineConverter;
import com.woo.coupon.converter.RuleConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description todo
 * @Author WooLeo
 * @Date 2020/2/29 9:19 上午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateResquest {

    // 优惠劵名称
    private String name;

    // 优惠劵 logo
    private String logo;

    // 描述
    private String desc;

    // 优惠劵分类
    private String category;

    // 产品线
    private Integer productLine;

    // 优惠劵总数
    private Integer count;

    private Long userId;

    // 目标用户
    private Integer target;

    // 优惠劵规则
    private TemplateRule rule;

    /**
     * @title
     * @description 校验
     * @author WooLeo
     * @updateTime 2020/2/29 9:51 上午
     * @throws
     */
    public boolean validate(){
        boolean stringValid = StringUtils.isNotEmpty(name) &&
                StringUtils.isNotEmpty(logo) && StringUtils.isNotEmpty(desc);

        boolean enumValid = null != CouponCategory.of(category) &&
                null != ProductLine.of(productLine) &&
                null != DistributeTarget.of(target);

        boolean numValid = count > 0 && userId > 0;

        return stringValid && enumValid && numValid && rule.validate();
    }
}
