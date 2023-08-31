package com.study.base.boot.aggregations.v1.order.domain.enumerations;

public enum ItemStatusEnum {
    SELL("판매"),
    STOP("판매중단")
    ;

    private String status;

    ItemStatusEnum(String status) {
        this.status  =  status;
    }
}
