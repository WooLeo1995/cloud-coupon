package com.woo.coupon.converter;

import com.woo.coupon.constant.DistributeTarget;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @Description 优惠劵用户类型枚举转换器
 * @Author WooLeo
 * @Date 2020/2/26 9:17 上午
 * @Version 1.0
 */
@Converter
public class DistributeTargetConverter implements AttributeConverter<DistributeTarget, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DistributeTarget distributeTarget) {
        return distributeTarget.getCode();
    }

    @Override
    public DistributeTarget convertToEntityAttribute(Integer code) {
        return DistributeTarget.of(code);
    }
}
