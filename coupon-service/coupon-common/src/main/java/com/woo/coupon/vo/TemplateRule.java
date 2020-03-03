package com.woo.coupon.vo;

import com.woo.coupon.constant.PeriodType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @Description 优惠劵规则
 * @Author WooLeo
 * @Date 2020/2/25 3:39 下午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateRule {

    // 优惠劵过期规则
    private Expiration expiration;
    // 折扣规则
    private Discount discount;
    // 数量限制 优惠劵领取上限
    private Integer limitation;

    // 使用范围
    private Usage usage;

    // 权重 （可以和哪些优惠劵叠加使用, 同一类型劵不可叠加使用）list[]
    private String weight;

    public boolean validate(){
        return expiration.validate() && discount.validate() && limitation > 0 &&
                usage.validate() && StringUtils.isNotEmpty(weight);
    }

    /**
     * 有效期限规则
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Expiration {
        // 有效期规则， 对应 PeriodType 的 code 字段
        private Integer period;
        // 有效期间隔： 只对变动性有效期有效
        private Integer gap;
        // 优惠劵模版的失效日期， 两类规则都有效
        private Long deadLine;

        boolean validate() {
            return null != PeriodType.of(period) && gap >0 && deadLine > 0;
        }
    }

    /**
     * 折扣 需要配合类型决定
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Discount{
        // 额度: 满减， 立减， 折扣
        private Integer quota;
        // 基准 需要满一定规则可用 （满多少可减）
        private Integer base;

        boolean validate() {
            return quota > 0 && base > 0;
        }
    }

    /**
     * 使用范围
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static  class Usage{
        // 区域
        private String area;
        // 城市
        private String city;
        // 商品类型
        private String productType;

        boolean validate() {
            return StringUtils.isNotBlank(area)
                    && StringUtils.isNotBlank(city)
                    && StringUtils.isNotBlank(productType);
        }
    }
}
