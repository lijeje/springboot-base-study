package com.study.base.boot.aggregations.v1.order.domain.enumerations;

public enum OrderStatusEnum {
    ORDER("주문"),
    CANCELED("취소")
    ;

    private String status;

    OrderStatusEnum(String status) {
        this.status  =  status;
    }
}
