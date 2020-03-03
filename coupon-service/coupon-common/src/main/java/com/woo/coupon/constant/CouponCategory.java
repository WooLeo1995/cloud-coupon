package com.woo.coupon.constant;

import jdk.nashorn.internal.ir.ReturnNode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum CouponCategory {

    MANJIAN("满减劵", "001"),
    ZHEKOU("折扣劵","002"),
    LIJIAN("立减劵","003");

    private String description;

    private String code;

    /**
     * 依据code 返回对应的枚举值
     * @param code
     * @return
     */
    public static CouponCategory of(String code){
        Objects.requireNonNull(code);

        return Stream.of(values())
                .filter(bean -> bean.code.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(code + " not exists!"));
    }
}
