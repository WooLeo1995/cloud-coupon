package com.woo.coupon.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum ProductLine {

    SPT("运营商", 1),
    SPE("企业",2);

    private String productLineName;

    private Integer productID;

    public static ProductLine of(Integer productID){
        Objects.requireNonNull(productID);

        return Stream.of(values())
                .filter(bean -> bean.productID.equals(productID))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(productID + " not exists!"));

    }
}
