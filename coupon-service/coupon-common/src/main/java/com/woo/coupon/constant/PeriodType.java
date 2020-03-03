package com.woo.coupon.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum PeriodType {


    REGULAR("固定的(固定日期)", 1),
    SHIFT("变动的(以领取日开始计算)", 2);

    private String description;

    private Integer code;

    public static PeriodType of(Integer code){
        Objects.requireNonNull(code);

        return Stream.of(values())
                .filter(bean -> bean.code.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(code + " not exists!"));
    }
}
