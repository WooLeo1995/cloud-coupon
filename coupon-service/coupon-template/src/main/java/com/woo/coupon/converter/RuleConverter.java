package com.woo.coupon.converter;

import com.alibaba.fastjson.JSON;
import com.woo.coupon.vo.TemplateRule;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @Description 规则转换器
 * @Author WooLeo
 * @Date 2020/2/26 9:21 上午
 * @Version 1.0
 */
@Converter
public class RuleConverter implements AttributeConverter<TemplateRule, String> {
    @Override
    public String convertToDatabaseColumn(TemplateRule templateRule) {
        return JSON.toJSONString(templateRule);
    }

    @Override
    public TemplateRule convertToEntityAttribute(String rule) {
        return JSON.parseObject(rule, TemplateRule.class);
    }
}
