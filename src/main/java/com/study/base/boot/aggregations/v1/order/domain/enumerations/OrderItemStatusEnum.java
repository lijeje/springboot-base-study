package com.study.base.boot.aggregations.v1.order.domain.enumerations;

public enum OrderItemStatusEnum {
    ORDER("주문"),
    CANCELED("취소"),
    PARTIAL_CANCELED("부분취소")
    ;

    private String status;

    OrderItemStatusEnum(String status) {
        this.status  =  status;
    }
}
