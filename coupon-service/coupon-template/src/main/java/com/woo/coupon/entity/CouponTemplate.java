package com.woo.coupon.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.woo.coupon.constant.CouponCategory;
import com.woo.coupon.constant.DistributeTarget;
import com.woo.coupon.constant.ProductLine;
import com.woo.coupon.converter.CouponCategoryConverter;
import com.woo.coupon.converter.DistributeTargetConverter;
import com.woo.coupon.converter.ProductLineConverter;
import com.woo.coupon.converter.RuleConverter;
import com.woo.coupon.serialization.CouponTemplateSerialize;
import com.woo.coupon.vo.TemplateRule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 优惠劵模版实体
 * @Author WooLeo
 * @Date 2020/2/25 4:14 下午
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "coupon_template")
@JsonSerialize(using = CouponTemplateSerialize.class)
public class CouponTemplate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    // 是否是可用状态
    @Column(name = "available", nullable = false)
    private Boolean available;

    // 是否过期
    @Column(name = "expired", nullable = false)
    private Boolean expired;

    // 优惠劵名称
    @Column(name = "name", nullable = false)
    private String name;

    // 优惠劵 logo
    @Column(name = "logo", nullable = false)
    private String logo;

    // 描述
    @Column(name = "intro", nullable = false)
    private String desc;

    // 优惠劵分类
    @Column(name = "category", nullable = false)
    @Convert(converter = CouponCategoryConverter.class)
    private CouponCategory category;

    // 产品线
    @Column(name = "productLine", nullable = false)
    @Convert(converter = ProductLineConverter.class)
    private ProductLine productLine;

    // 优惠劵总数
    @Column(name = "coupon_count", nullable = false)
    private Integer count;

    @CreatedDate
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    // 优惠劵模版的编码
    @Column(name = "template_key", nullable = false)
    private String key;

    // 目标用户
    @Column(name = "target", nullable = false)
    @Convert(converter = DistributeTargetConverter.class)
    private DistributeTarget target;

    // 优惠劵规则
    @Column(name = "rule", nullable = false)
    @Convert(converter = RuleConverter.class)
    private TemplateRule rule;

    public CouponTemplate(String name, String logo, String desc, String category,
                          Integer productLine, Integer count, Long userId,
                          Integer target, TemplateRule rule) {
        this.available = false;
        this.expired = false;
        this.name = name;
        this.logo = logo;
        this.desc = desc;
        this.category = CouponCategory.of(category);
        this.productLine = ProductLine.of(productLine);
        this.count = count;
        this.userId = userId;
        // 优惠劵唯一编码 = 4(产品线和类型) + 8(日期) + id(扩充4位)
        this.key = productLine.toString() + category +
                new SimpleDateFormat("yyyyMMdd");
        this.target = DistributeTarget.of(target);
        this.rule = rule;
    }
}
